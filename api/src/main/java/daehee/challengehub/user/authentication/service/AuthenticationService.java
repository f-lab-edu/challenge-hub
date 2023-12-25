package daehee.challengehub.user.authentication.service;

import daehee.challengehub.user.authentication.entity.User;
import daehee.challengehub.user.authentication.model.PasswordChangeDto;
import daehee.challengehub.user.authentication.model.UserLoginDto;
import daehee.challengehub.user.authentication.model.UserSignupDto;
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
    public User signup(UserSignupDto userSignupDto) {
        authenticationRepository.save(userSignupDto);
        return userSignupDto.toEntity(); // UserSignupDto를 User 엔티티로 변환
    }

    // 이메일 인증 로직
    public boolean verifyEmail(String token) {
        return authenticationRepository.isEmailVerified(token);
    }

    // 로그인 로직
    public User login(UserLoginDto userLoginDto) {
        boolean isValidLogin = authenticationRepository.validateLogin(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        if (isValidLogin) {
            return authenticationRepository.findByEmail(userLoginDto.getEmail());
        } else {
            return null;
        }
    }

    // 비밀번호 재설정 로직
    public boolean resetPassword(PasswordChangeDto passwordChangeDto) {
        boolean isCorrectPassword = authenticationRepository.validateLogin(
                passwordChangeDto.getEmail(), passwordChangeDto.getCurrentPassword());

        if (isCorrectPassword) {
            return authenticationRepository.updatePassword(
                    passwordChangeDto.getEmail(), passwordChangeDto.getNewPassword());
        } else {
            return false;
        }
    }
}
