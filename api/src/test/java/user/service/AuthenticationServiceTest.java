package user.service;

import daehee.challengehub.user.authentication.entity.User;
import daehee.challengehub.user.authentication.model.PasswordChangeDto;
import daehee.challengehub.user.authentication.model.UserLoginDto;
import daehee.challengehub.user.authentication.model.UserSignupDto;
import daehee.challengehub.user.authentication.repository.AuthenticationRepository;
import daehee.challengehub.user.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
        User user = signupDto.toEntity();
        when(authenticationRepository.findByEmail(signupDto.getEmail())).thenReturn(user);

        // When
        User result = authenticationService.signup(signupDto);

        // Then
        assertNotNull(result);
        assertEquals(signupDto.getUsername(), result.getUsername());
        assertEquals(signupDto.getEmail(), result.getEmail());
        verify(authenticationRepository).save(any(UserSignupDto.class));
    }


    @Test
    public void testVerifyEmail_ValidToken() {
        // Given
        String validToken = "validToken123";
        when(authenticationRepository.isEmailVerified(validToken)).thenReturn(true);

        // When
        boolean isVerified = authenticationService.verifyEmail(validToken);

        // Then
        assertTrue(isVerified);
    }

    @Test
    public void testVerifyEmail_InvalidToken() {
        // Given
        String invalidToken = "invalidToken123";
        when(authenticationRepository.isEmailVerified(invalidToken)).thenReturn(false);

        // When & Then
        assertFalse(authenticationService.verifyEmail(invalidToken));
    }

    @Test
    public void testLogin_Success() {
        // Given
        UserLoginDto loginDto = UserLoginDto.builder()
                .email("user@example.com")
                .password("password123")
                .build();
        when(authenticationRepository.validateLogin(loginDto.getEmail(), loginDto.getPassword())).thenReturn(true);
        when(authenticationRepository.findByEmail(loginDto.getEmail())).thenReturn(new User());

        // When
        User result = authenticationService.login(loginDto);

        // Then
        assertNotNull(result);
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
        assertNull(authenticationService.login(loginDto));
    }

    @Test
    public void testResetPassword() {
        // Given
        PasswordChangeDto passwordChangeDto = PasswordChangeDto.builder()
                .currentPassword("oldPassword")
                .newPassword("newPassword")
                .build();
        when(authenticationRepository.validateLogin(anyString(), eq("oldPassword"))).thenReturn(true);

        // When
        boolean isReset = authenticationService.resetPassword(passwordChangeDto);

        // Then
        assertTrue(isReset);
        verify(authenticationRepository).updatePassword(anyString(), eq("newPassword"));
    }

}
