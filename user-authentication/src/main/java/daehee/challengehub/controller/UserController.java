package daehee.challengehub.controller;

import daehee.challengehub.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

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
                .token("sampleToken")
                .build();

        // TODO: 임의의 값을 내가 다시 넣어서 시나리오 완성 시키기
        String responseMessage;
        switch (newUser.getToken()) {
            case "validToken":
                responseMessage = String.format("소셜 계정(%s)으로 회원가입 성공. 토큰: %s",
                        newUser.getProvider(), newUser.getToken());
                break;
            case "expiredToken":
                responseMessage = "소셜 로그인 실패: 만료된 토큰";
                break;
            default:
                responseMessage = "소셜 로그인 실패: 잘못된 토큰";
        }

        return ResponseEntity.ok(responseMessage);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 로그인 데이터 생성
        UserLoginDto loginUser = UserLoginDto.builder()
                .email("user@example.com")
                .password("password123")
                .rememberMe(true)
                .twoFactorAuthCode("2FA123")
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
        // 임의의 소셜 로그인 데이터 생성
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


    // 2단계 인증
    @PostMapping("/login/2fa")
    public ResponseEntity<String> twoFactorAuthentication(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 2단계 인증 데이터 생성
        UserLoginDto twoFaUser = UserLoginDto.builder()
                .email("2fauser@example.com")
                .twoFactorAuthCode("2FA123456")
                .build();

        String responseMessage;
        if ("2FA123456".equals(twoFaUser.getTwoFactorAuthCode())) {
            responseMessage = "2단계 인증 성공: 이메일 - " + twoFaUser.getEmail();
        } else {
            responseMessage = "2단계 인증 실패: 잘못된 인증 코드";
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


    // 프로필 조회
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        // 성공적인 프로필 조회 시나리오
        UserProfileDto successfulProfile = UserProfileDto.builder()
                .username("sampleUser")
                .email("user@example.com")
                .bio("Sample bio")
                .location("Sample location")
                .build();

        // 실패한 프로필 조회 시나리오 (예: 프로필이 존재하지 않음)
        String failureMessage = "프로필 조회 실패: 사용자를 찾을 수 없습니다.";

        // 임의로 성공 또는 실패 시나리오 선택
        boolean isSuccessful = true; // 이 값을 변경하여 성공/실패 시나리오 선택
        return isSuccessful ? ResponseEntity.ok(successfulProfile) : ResponseEntity.badRequest().body(failureMessage);
    }


    // 프로필 업데이트
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDto userProfileDto) {
        // 임의의 유저 프로필 업데이트 데이터 생성
        UserProfileDto updatedProfile = UserProfileDto.builder()
                .username("updatedUser")
                .email("updated@example.com")
                .bio("Updated bio")
                .location("Updated location")
                .build();

        String responseMessage = "프로필 업데이트 성공: " + updatedProfile.getUsername();
        return ResponseEntity.ok(responseMessage);
    }

    // 비밀번호 변경
    @PutMapping("/profile/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        PasswordChangeDto newPasswordData = PasswordChangeDto.builder()
                .currentPassword("oldPassword")
                .newPassword("newStrongPassword")
                .build();

        String responseMessage = "비밀번호 변경 성공: " + newPasswordData.getNewPassword();
        return ResponseEntity.ok(responseMessage);
    }

    // 프로필 이미지 업로드
    @PostMapping("/profile/avatar")
    public ResponseEntity<String> uploadAvatar() {
        // 임의의 파일 생성
//        byte[] content = "dummy image content".getBytes();
//        MultipartFile mockFile = new MockMultipartFile("user-avatar", "avatar.jpg", "image/jpeg", content);
//
//        ProfileImageUploadDto newAvatar = ProfileImageUploadDto.builder()
//                .imageFile(mockFile)
//                .build();
//
//        String responseMessage = "프로필 이미지 업로드 성공: " + newAvatar.getImageFile().getOriginalFilename();
//        return ResponseEntity.ok(responseMessage);
        return null;
    }

    // 성과 목록 조회
    @GetMapping("/profile/achievements")
    public ResponseEntity<List<String>> getAchievements() {
        // 임의의 성과 목록 생성
        List<String> achievements = Arrays.asList(
                "10일 연속 챌린지 완료",
                "커뮤니티에서 활발한 활동",
                "첫 챌린지 성공",
                "최다 좋아요 획득"
        );
        return ResponseEntity.ok(achievements);
    }

}
