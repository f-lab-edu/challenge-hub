package daehee.challengehub.user.authentication.controller;


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
        return authenticationService.signup(userSignupDto);
    }


    @GetMapping("/users/verify/{token}")
    public VerifyEmailResponseDto verifyEmail(@PathVariable String token) {
        return authenticationService.verifyEmail(token);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody UserLoginDto userLoginDto) {
        return authenticationService.login(userLoginDto);
    }

    @PostMapping("/password/reset")
    public ResetPasswordResponseDto resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        return authenticationService.resetPassword(passwordChangeDto);
    }
}
