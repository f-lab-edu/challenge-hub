package daehee.challengehub.user.authentication.controller;


import daehee.challengehub.user.authentication.entity.User;
import daehee.challengehub.user.authentication.model.LoginResponseDto;
import daehee.challengehub.user.authentication.model.PasswordChangeDto;
import daehee.challengehub.user.authentication.model.ResetPasswordResponseDto;
import daehee.challengehub.user.authentication.model.SignupResponseDto;
import daehee.challengehub.user.authentication.model.UserLoginDto;
import daehee.challengehub.user.authentication.model.UserSignupDto;
import daehee.challengehub.user.authentication.model.VerifyEmailResponseDto;
import daehee.challengehub.user.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/users")
    public SignupResponseDto signup(@RequestBody UserSignupDto userSignupDto) {
        User user = authenticationService.signup(userSignupDto);
        return new SignupResponseDto("회원가입 성공", user);
    }

    @GetMapping("/users/verify/{token}")
    public VerifyEmailResponseDto verifyEmail(@PathVariable String token) {
        boolean isVerified = authenticationService.verifyEmail(token);
        return isVerified ?
                new VerifyEmailResponseDto("이메일 인증 성공", token) :
                new VerifyEmailResponseDto("이메일 인증 실패", token);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody UserLoginDto userLoginDto) {
        User user = authenticationService.login(userLoginDto);
        return user != null ?
                new LoginResponseDto("로그인 성공", user.getEmail()) :
                new LoginResponseDto("로그인 실패", null);
    }

    @PostMapping("/password/reset")
    public ResetPasswordResponseDto resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        boolean isReset = authenticationService.resetPassword(passwordChangeDto);
        return isReset ?
                new ResetPasswordResponseDto("비밀번호 재설정 성공", passwordChangeDto.getNewPassword()) :
                new ResetPasswordResponseDto("비밀번호 재설정 실패", null);
    }
}

