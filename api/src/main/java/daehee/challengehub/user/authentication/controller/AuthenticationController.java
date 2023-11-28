package daehee.challengehub.user.authentication.controller;

import daehee.challengehub.user.authentication.model.PasswordChangeDto;
import daehee.challengehub.user.authentication.model.UserLoginDto;
import daehee.challengehub.user.authentication.model.UserSignupDto;
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
    public Map<String, Object> signup(@RequestBody UserSignupDto userSignupDto) {
        return authenticationService.signup(userSignupDto);
    }

    @GetMapping("/users/verify/{token}")
    public Map<String, String> verifyEmail(@PathVariable String token) {
        return authenticationService.verifyEmail(token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginDto userLoginDto) {
        return authenticationService.login(userLoginDto);
    }

    @PostMapping("/password/reset")
    public Map<String, String> resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        return authenticationService.resetPassword(passwordChangeDto);
    }
}
