package daehee.challengehub.authentication.controller;

import daehee.challengehub.authentication.model.PasswordChangeDto;
import daehee.challengehub.authentication.model.UserLoginDto;
import daehee.challengehub.authentication.model.UserSignupDto;
import daehee.challengehub.authentication.model.UserSocialLoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    // 회원가입
    @PostMapping("/users")
    public ResponseEntity<String> signup(@RequestBody UserSignupDto userSignupDto) {
        // 시나리오 1: 표준 회원가입1
        UserSignupDto standardUser = UserSignupDto.builder()
                .username("standardUser")
                .email("standard@example.com")
                .password("password123")
                .build();

        // 시나리오 2: 관리자 계정 회원가입, TODO: 관리자 구분을 위한 필드를 추가 해야하나?, 굳이 관리자 계정이 필요 없을 수도?
        UserSignupDto adminUser = UserSignupDto.builder()
                .username("adminUser")
                .email("admin@example.com")
                .password("adminPassword")
                .build();

        // 시나리오 3: 표준 회원가입2
        UserSignupDto guestUser = UserSignupDto.builder()
                .username("guestUser")
                .email("guest@example.com")
                .password("guestPassword")
                .build();

        String responseMessage = String.format("회원가입 성공: %s, %s, %s",
                standardUser.getUsername(), adminUser.getUsername(), guestUser.getUsername());
        return ResponseEntity.ok(responseMessage);
    }


    // 이메일 인증
    @GetMapping("/users/verify/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) {
        // 임의의 토큰 값 설정
        token = "validToken123";
        String fakeValidToken = "validToken123";
        String fakeExpiredToken = "expiredToken123";
        String fakeInvalidToken = "invalidToken123";

        if (token.equals(fakeValidToken)) {
            return ResponseEntity.ok("이메일 인증 성공. 토큰: " + token);
        } else if (token.equals(fakeExpiredToken)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패: 만료된 토큰");
        } else if (token.equals(fakeInvalidToken)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패: 잘못된 토큰");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패: 알 수 없는 오류");
        }
    }


    // 소셜 계정으로 회원가입
    @PostMapping("/users/social")
    public ResponseEntity<String> signupWithSocial() {
        // 임의의 소셜 계정 정보 생성
        UserSocialLoginDto newUser = UserSocialLoginDto.builder()
                .provider("Google")
                .token("validToken")
                .build();

        String responseMessage = switch (newUser.getToken()) {
            case "validToken" -> "소셜 계정으로 회원가입 성공";
            case "expiredToken" -> "소셜 로그인 실패: 만료된 토큰";
            default -> "소셜 로그인 실패: 잘못된 토큰";
        };

        return ResponseEntity.ok(responseMessage);
    }


    // 이메일 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 로그인 데이터 생성
        UserLoginDto loginUser = UserLoginDto.builder()
                .email("user@example.com")
                .password("password123")
                .rememberMe(true)
                .build();

        String responseMessage;
        if ("user@example.com".equals(loginUser.getEmail()) && "password123".equals(loginUser.getPassword())) {
            responseMessage = "로그인 성공: " + loginUser.getEmail();
        } else {
            responseMessage = "로그인 실패: 잘못된 이메일 또는 비밀번호";
        }

        return ResponseEntity.ok(responseMessage);
    }


    // 소셜 로그인
    @PostMapping("/login/social")
    public ResponseEntity<String> loginWithSocial(@RequestBody UserSocialLoginDto userSocialLoginDto) {
        UserSocialLoginDto socialLoginUser = UserSocialLoginDto.builder()
                .provider("Facebook")
                .token("sampleTokenFacebook123")
                .build();

        String responseMessage;
        if ("Facebook".equals(socialLoginUser.getProvider()) && "sampleTokenFacebook123".equals(socialLoginUser.getToken())) {
            responseMessage = "소셜 로그인 성공: 프로바이더 - " + socialLoginUser.getProvider();
        } else {
            responseMessage = "소셜 로그인 실패: 잘못된 프로바이더 또는 토큰";
        }

        return ResponseEntity.ok(responseMessage);
    }


    // 비밀번호 재설정
    @PostMapping("/password/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        // 성공적인 비밀번호 변경 시나리오
        PasswordChangeDto successfulChange = PasswordChangeDto.builder()
                .currentPassword("currentPassword123")
                .newPassword("newPassword456")
                .build();

        String successMessage = "비밀번호 재설정 성공: 새 비밀번호 - " + successfulChange.getNewPassword();

        // 실패한 비밀번호 변경 시나리오 (예: 현재 비밀번호 불일치)
        PasswordChangeDto failedChange = PasswordChangeDto.builder()
                .currentPassword("wrongCurrentPassword")
                .newPassword("newPassword789")
                .build();

        String failureMessage = "비밀번호 재설정 실패: 현재 비밀번호 불일치";

        // 임의로 성공 또는 실패 시나리오 선택
        boolean isSuccessful = true; // 이 값을 변경하여 성공/실패 시나리오 선택
        return ResponseEntity.ok(isSuccessful ? successMessage : failureMessage);
    }
}
