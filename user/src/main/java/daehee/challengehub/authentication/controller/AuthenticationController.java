package daehee.challengehub.authentication.controller;

import daehee.challengehub.authentication.model.PasswordChangeDto;
import daehee.challengehub.authentication.model.UserLoginDto;
import daehee.challengehub.authentication.model.UserSignupDto;
import daehee.challengehub.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
