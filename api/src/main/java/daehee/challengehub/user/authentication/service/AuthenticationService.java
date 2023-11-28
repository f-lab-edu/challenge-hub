package daehee.challengehub.user.authentication.service;

import daehee.challengehub.user.authentication.model.PasswordChangeDto;
import daehee.challengehub.user.authentication.model.UserLoginDto;
import daehee.challengehub.user.authentication.model.UserSignupDto;
import daehee.challengehub.user.authentication.repository.AuthenticationRepository;
import daehee.challengehub.common.constants.ErrorCode;
import daehee.challengehub.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    // 회원가입 로직
    public Map<String, Object> signup(UserSignupDto userSignupDto) {
        // 레포지토리를 통해 사용자 정보 저장
        authenticationRepository.save(userSignupDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "회원가입 성공");
        response.put("user", userSignupDto);
        return response;
    }

    // 이메일 인증 로직
    public Map<String, String> verifyEmail(String token) {
        if (authenticationRepository.isEmailVerified(token)) {
            return Map.of("message", "이메일 인증 성공", "token", token);
        } else {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }
    }

    // 로그인 로직
    public Map<String, String> login(UserLoginDto userLoginDto) {
        boolean isValidLogin = authenticationRepository.validateLogin(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        if (isValidLogin) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "로그인 성공");
            response.put("userEmail", userLoginDto.getEmail());
            return response;
        } else {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    // 비밀번호 재설정 로직
    public Map<String, String> resetPassword(PasswordChangeDto passwordChangeDto) {
        // 레포지토리를 통해 비밀번호 업데이트
        authenticationRepository.updatePassword(
                passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());

        Map<String, String> response = new HashMap<>();
        response.put("message", "비밀번호 재설정 성공");
        response.put("newPassword", passwordChangeDto.getNewPassword());
        return response;
    }
}
