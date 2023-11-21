import daehee.challengehub.authentication.model.PasswordChangeDto;
import daehee.challengehub.authentication.model.UserLoginDto;
import daehee.challengehub.authentication.model.UserSignupDto;
import daehee.challengehub.authentication.repository.AuthenticationRepository;
import daehee.challengehub.authentication.service.AuthenticationService;
import daehee.challengehub.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private AuthenticationRepository authenticationRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testSignup() {
        // Given
        UserSignupDto signupDto = UserSignupDto.builder()
                .username("standardUser")
                .email("standard@example.com")
                .password("password123")
                .build();
        // When
        Map<String, Object> response = authenticationService.signup(signupDto);
        // Then
        assertEquals("회원가입 성공", response.get("message"));
        verify(authenticationRepository).save(signupDto);
    }

    @Test
    public void testVerifyEmail_ValidToken() {
        // Given
        String validToken = "validToken123";
        when(authenticationRepository.isEmailVerified(validToken)).thenReturn(true);
        // When
        Map<String, String> response = authenticationService.verifyEmail(validToken);
        // Then
        assertEquals("이메일 인증 성공", response.get("message"));
    }

    @Test
    public void testVerifyEmail_InvalidToken() {
        // Given
        String invalidToken = "invalidToken123";
        when(authenticationRepository.isEmailVerified(invalidToken)).thenReturn(false);
        // When & Then
        assertThrows(CustomException.class, () -> authenticationService.verifyEmail(invalidToken));
    }

    @Test
    public void testLogin_Success() {
        // Given
        UserLoginDto loginDto = UserLoginDto.builder()
                .email("user@example.com")
                .password("password123")
                .build();
        when(authenticationRepository.validateLogin(loginDto.getEmail(), loginDto.getPassword())).thenReturn(true);
        // When
        Map<String, String> response = authenticationService.login(loginDto);
        // Then
        assertEquals("로그인 성공", response.get("message"));
    }

    @Test
    public void testLogin_Failure() {
        // Given
        UserLoginDto loginDto = UserLoginDto.builder()
                .email("wrong@example.com")
                .password("wrongPassword")
                .build();
        when(authenticationRepository.validateLogin(loginDto.getEmail(), loginDto.getPassword())).thenReturn(false);
        // When & Then
        assertThrows(CustomException.class, () -> authenticationService.login(loginDto));
    }

    @Test
    public void testResetPassword() {
        // Given
        PasswordChangeDto passwordChangeDto = PasswordChangeDto.builder()
                .currentPassword("oldPassword")
                .newPassword("newPassword")
                .build();

        // When
        Map<String, String> response = authenticationService.resetPassword(passwordChangeDto);

        // Then
        assertEquals("비밀번호 재설정 성공", response.get("message"));
        verify(authenticationRepository).updatePassword(anyString(), eq("newPassword"));
    }
}
