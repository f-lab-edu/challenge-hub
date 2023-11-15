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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    // 회원가입
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody UserSignupDto userSignupDto) {
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "회원가입 성공");
        response.put("user", standardUser);
        return ResponseEntity.ok(response);
    }


    // 이메일 인증
    @GetMapping("/users/verify/{token}")
    public ResponseEntity<Map<String, String>> verifyEmail(@PathVariable String token) {
        Map<String, String> response = new HashMap<>();
        String fakeValidToken = "validToken123";
        String fakeExpiredToken = "expiredToken123";
        String fakeInvalidToken = "invalidToken123";

        if (token.equals(fakeValidToken)) {
            response.put("message", "이메일 인증 성공");
            response.put("token", fakeValidToken);
            return ResponseEntity.ok(response);
        } else if (token.equals(fakeExpiredToken)) {
            response.put("error", "인증 실패: 만료된 토큰");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (token.equals(fakeInvalidToken)) {
            response.put("error", "인증 실패: 잘못된 토큰");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            response.put("error", "인증 실패: 알 수 없는 오류");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    // 소셜 계정으로 회원가입
    @PostMapping("/users/social")
    public ResponseEntity<Map<String, String>> signupWithSocial() {
        // 임의의 소셜 계정 정보 생성
        UserSocialLoginDto newUser = UserSocialLoginDto.builder()
                .provider("Google")
                .token("validToken")
                .build();

        Map<String, String> response = new HashMap<>();
        switch (newUser.getToken()) {
            case "validToken":
                response.put("message", "소셜 계정으로 회원가입 성공");
                break;
            case "expiredToken":
                response.put("error", "소셜 로그인 실패: 만료된 토큰");
                break;
            default:
                response.put("error", "소셜 로그인 실패: 잘못된 토큰");
                break;
        }

        return ResponseEntity.ok(response);
    }

    // 이메일 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 로그인 데이터 생성
        UserLoginDto loginUser = UserLoginDto.builder()
                .email("user@example.com")
                .password("password123")
                .rememberMe(true)
                .build();

        Map<String, String> response = new HashMap<>();
        if ("user@example.com".equals(loginUser.getEmail()) && "password123".equals(loginUser.getPassword())) {
            response.put("message", "로그인 성공");
            response.put("userEmail", loginUser.getEmail());
        } else {
            response.put("error", "로그인 실패: 잘못된 이메일 또는 비밀번호");
        }

        return ResponseEntity.ok(response);
    }



    // 소셜 로그인
    @PostMapping("/login/social")
    public ResponseEntity<Map<String, String>> loginWithSocial(@RequestBody UserSocialLoginDto userSocialLoginDto) {
        UserSocialLoginDto socialLoginUser = UserSocialLoginDto.builder()
                .provider("Facebook")
                .token("sampleTokenFacebook123")
                .build();

        Map<String, String> response = new HashMap<>();
        if ("Facebook".equals(socialLoginUser.getProvider()) && "sampleTokenFacebook123".equals(socialLoginUser.getToken())) {
            response.put("message", "소셜 로그인 성공");
            response.put("token", socialLoginUser.getToken());
        } else {
            response.put("error", "소셜 로그인 실패: 잘못된 프로바이더 또는 토큰");
        }

        return ResponseEntity.ok(response);
    }


    // 비밀번호 재설정
    @PostMapping("/password/reset")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        PasswordChangeDto successfulChange = PasswordChangeDto.builder()
                .currentPassword("currentPassword123")
                .newPassword("newPassword456")
                .build();

        Map<String, String> response = new HashMap<>();
        boolean isSuccessful = true; // 임의로 성공/실패 시나리오 선택

        if (isSuccessful) {
            response.put("message", "비밀번호 재설정 성공");
            response.put("newPassword", successfulChange.getNewPassword());
        } else {
            response.put("error", "비밀번호 재설정 실패: 현재 비밀번호 불일치");
        }

        return ResponseEntity.ok(response);
    }

}
