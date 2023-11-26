package daehee.challengehub.user.authentication.controller;

import daehee.challengehub.user.authentication.model.*;
import daehee.challengehub.user.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
