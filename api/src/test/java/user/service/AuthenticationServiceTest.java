package user.service;

import daehee.challengehub.common.exception.CustomException;
import daehee.challengehub.user.authentication.model.*;
import daehee.challengehub.user.authentication.repository.AuthenticationRepository;
import daehee.challengehub.user.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        SignupResponseDto response = authenticationService.signup(signupDto);

        // Then
        assertEquals("회원가입 성공", response.getMessage());
        assertEquals(signupDto, response.getUser());
        verify(authenticationRepository).save(signupDto);
    }


    @Test
    public void testVerifyEmail_ValidToken() {
        // Given
        String validToken = "validToken123";
        when(authenticationRepository.isEmailVerified(validToken)).thenReturn(true);

        // When
        VerifyEmailResponseDto response = authenticationService.verifyEmail(validToken);

        // Then
        assertEquals("이메일 인증 성공", response.getMessage());
        assertEquals(validToken, response.getToken());
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
        LoginResponseDto response = authenticationService.login(loginDto);

        // Then
        assertEquals("로그인 성공", response.getMessage());
        assertEquals(loginDto.getEmail(), response.getUserEmail());
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
        ResetPasswordResponseDto response = authenticationService.resetPassword(passwordChangeDto);

        // Then
        assertEquals("비밀번호 재설정 성공", response.getMessage());
        assertEquals("newPassword", response.getNewPassword());
        verify(authenticationRepository).updatePassword(anyString(), eq("newPassword"));
    }

}
