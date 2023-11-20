import daehee.challengehub.authentication.model.PasswordChangeDto;
import daehee.challengehub.authentication.model.UserLoginDto;
import daehee.challengehub.authentication.model.UserSignupDto;
import daehee.challengehub.authentication.repository.AuthenticationRepository;
import daehee.challengehub.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
                .nickname("StandardNickname")
                .phoneNumber("1234567890")
                .build();

        when(authenticationRepository.findByEmail("standard@example.com")).thenReturn(null);

        // When
        Map<String, Object> response = authenticationService.signup(signupDto);

        // Then
        assertEquals("회원가입 성공", response.get("message"));
        verify(authenticationRepository).save(any(UserSignupDto.class));
    }


    @Test
    public void testVerifyEmail() {
        // Given
        String validToken = "validToken123";
        when(authenticationRepository.isEmailVerified(validToken)).thenReturn(true);

        // When
        Map<String, String> response = authenticationService.verifyEmail(validToken);

        // Then
        assertEquals("이메일 인증 성공", response.get("message"));
    }

    @Test
    public void testLogin() {
        // Given
        UserLoginDto loginDto = new UserLoginDto("user@example.com", "password123", true);
        when(authenticationRepository.validateLogin("user@example.com", "password123")).thenReturn(true);

        // When
        Map<String, String> response = authenticationService.login(loginDto);

        // Then
        assertEquals("로그인 성공", response.get("message"));
        assertEquals("user@example.com", response.get("userEmail"));
    }

    @Test
    public void testResetPassword() {
        // Given
        PasswordChangeDto passwordChangeDto = new PasswordChangeDto("oldPassword", "newPassword");
        UserSignupDto mockUser = UserSignupDto.builder()
                .username("user")
                .email("email@example.com")
                .password("oldPassword")
                .nickname("UserNickname")
                .phoneNumber("1234567890")
                .build();

        when(authenticationRepository.findByEmail("email@example.com")).thenReturn(mockUser);

        // When
        Map<String, String> response = authenticationService.resetPassword(passwordChangeDto);

        // Then
        assertEquals("비밀번호 재설정 성공", response.get("message"));
        verify(authenticationRepository).updatePassword("email@example.com", "newPassword");
    }

}
