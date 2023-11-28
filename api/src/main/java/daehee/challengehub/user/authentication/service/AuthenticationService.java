package daehee.challengehub.user.authentication.service;

import daehee.challengehub.common.constants.ErrorCode;
import daehee.challengehub.common.exception.CustomException;
import daehee.challengehub.user.authentication.model.*;
import daehee.challengehub.user.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    // 회원가입 로직
    public SignupResponseDto signup(UserSignupDto userSignupDto) {
        // 레포지토리를 통해 사용자 정보 저장
        authenticationRepository.save(userSignupDto);

        // SignupResponseDto 반환
        return new SignupResponseDto("회원가입 성공", userSignupDto);
    }


    // 이메일 인증 로직
    public VerifyEmailResponseDto verifyEmail(String token) {
        if (authenticationRepository.isEmailVerified(token)) {
            // VerifyEmailResponseDto 반환
            return new VerifyEmailResponseDto("이메일 인증 성공", token);
        } else {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }
    }


    // 로그인 로직
    public LoginResponseDto login(UserLoginDto userLoginDto) {
        boolean isValidLogin = authenticationRepository.validateLogin(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        if (isValidLogin) {
            // LoginResponseDto 반환
            return new LoginResponseDto("로그인 성공", userLoginDto.getEmail());
        } else {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }
    }


    // 비밀번호 재설정 로직
    public ResetPasswordResponseDto resetPassword(PasswordChangeDto passwordChangeDto) {
        // 레포지토리를 통해 비밀번호 업데이트
        authenticationRepository.updatePassword(
                passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());

        // ResetPasswordResponseDto 반환
        return new ResetPasswordResponseDto("비밀번호 재설정 성공", passwordChangeDto.getNewPassword());
    }
}
