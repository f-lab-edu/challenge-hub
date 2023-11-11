package daehee.challengehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import daehee.challengehub.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> signup() {
        return ResponseEntity.ok(userService.signup());
    }

    @GetMapping("/users/verify/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) {
        return ResponseEntity.ok(userService.verifyEmail(token));
    }

    @PostMapping("/users/social")
    public ResponseEntity<String> signupWithSocial() {
        return ResponseEntity.ok(userService.signupWithSocial());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok(userService.login());
    }

    @PostMapping("/login/social")
    public ResponseEntity<String> loginWithSocial() {
        return ResponseEntity.ok(userService.loginWithSocial());
    }

    @PostMapping("/login/2fa")
    public ResponseEntity<String> twoFactorAuthentication() {
        return ResponseEntity.ok(userService.twoFactorAuthentication());
    }

    @PostMapping("/password/reset")
    public ResponseEntity<String> resetPassword() {
        return ResponseEntity.ok(userService.resetPassword());
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile() {
        return ResponseEntity.ok(userService.updateProfile());
    }

    @PutMapping("/profile/password")
    public ResponseEntity<String> changePassword() {
        return ResponseEntity.ok(userService.changePassword());
    }

    @PostMapping("/profile/avatar")
    public ResponseEntity<String> uploadAvatar() {
        return ResponseEntity.ok(userService.uploadAvatar());
    }

    @GetMapping("/profile/achievements")
    public ResponseEntity<String> getAchievements() {
        return ResponseEntity.ok(userService.getAchievements());
    }
}
