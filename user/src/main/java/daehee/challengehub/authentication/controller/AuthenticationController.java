package daehee.challengehub.authentication.controller;

import daehee.challengehub.authentication.model.PasswordChangeDto;
import daehee.challengehub.authentication.model.UserLoginDto;
import daehee.challengehub.authentication.model.UserSignupDto;
import daehee.challengehub.constants.ErrorCode;
import daehee.challengehub.exception.CustomException;
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
    public Map<String, Object> signup(@RequestBody UserSignupDto userSignupDto) {
        // 시나리오 1: 표준 회원가입1
        UserSignupDto standardUser = UserSignupDto.builder()
                .username("standardUser")
                .email("standard@example.com")
                .password("password123")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "회원가입 성공");
        response.put("user", standardUser);
        return response;
    }


    // 이메일 인증
    @GetMapping("/users/verify/{token}")
    public Map<String, String> verifyEmail(@PathVariable String token) {
        Map<String, String> response = new HashMap<>();
        String fakeValidToken = "validToken123";
        String fakeExpiredToken = "expiredToken123";
        String fakeInvalidToken = "invalidToken123";

        if (token.equals(fakeValidToken)) {
            return Map.of("message", "이메일 인증 성공", "token", token);
        } else if (token.equals(fakeExpiredToken)) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } else if (token.equals(fakeInvalidToken)) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        } else {
            throw new CustomException(ErrorCode.TOKEN_UNKNOWN_ERROR);
        }
    }

    // 이메일 로그인
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginDto userLoginDto) {
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
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        return response;
    }

    // 비밀번호 재설정
    @PostMapping("/password/reset")
    public Map<String, String> resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
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
            throw new CustomException(ErrorCode.PASSWORD_RESET_FAILED);
        }

        return response;
    }
}
