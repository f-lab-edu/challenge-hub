package daehee.challengehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import daehee.challengehub.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 엔드포인트
    @PostMapping("/signup")
    public ResponseEntity<String> signup() {
        return ResponseEntity.ok(userService.signup());
    }

    // 이메일 인증 엔드포인트
    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) {
        return ResponseEntity.ok(userService.verifyEmail(token));
    }

    // 소셜 계정으로 회원가입 엔드포인트
    @PostMapping("/signup/social")
    public ResponseEntity<String> signupWithSocial() {
        return ResponseEntity.ok(userService.signupWithSocial());
    }

    // 이메일 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok(userService.login());
    }

    // 소셜 로그인 엔드포인트
    @PostMapping("/login/social")
    public ResponseEntity<String> loginWithSocial() {
        return ResponseEntity.ok(userService.loginWithSocial());
    }

    // 2단계 인증 엔드포인트
    @PostMapping("/login/2fa")
    public ResponseEntity<String> twoFactorAuthentication() {
        return ResponseEntity.ok(userService.twoFactorAuthentication());
    }

    // 비밀번호 재설정 요청 엔드포인트
    @PostMapping("/password/reset")
    public ResponseEntity<String> resetPassword() {
        return ResponseEntity.ok(userService.resetPassword());
    }

    // 프로필 조회 엔드포인트
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    // 프로필 업데이트 엔드포인트
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile() {
        return ResponseEntity.ok(userService.updateProfile());
    }

    // 비밀번호 변경 엔드포인트
    @PutMapping("/profile/password")
    public ResponseEntity<String> changePassword() {
        return ResponseEntity.ok(userService.changePassword());
    }
}
