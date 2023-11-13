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
    public ResponseEntity<String> signupWithSocial(@RequestBody UserSocialLoginDto userSocialLoginDto) {
        // 임의의 소셜 계정 정보 생성
        UserSocialLoginDto newUser = UserSocialLoginDto.builder()
                .provider("Google")
                .token("sampleToken")
                .build();

        String responseMessage = String.format("소셜 계정(%s)으로 회원가입 성공. 토큰: %s",
                newUser.getProvider(), newUser.getToken());
        return ResponseEntity.ok(responseMessage);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    // 일반 로그인
=======
<<<<<<< HEAD

=======
>>>>>>> 1d47c3a (UPDATE : Controller 스켈레톤 코드 수정 중)
>>>>>>> c066573 (Resolve merge conflicts before rebase)
=======

>>>>>>> 4defbf2 (커밋 메시지)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 로그인 데이터 생성
        UserLoginDto loginUser = UserLoginDto.builder()
                .email("user@example.com")
                .password("password123")
                .rememberMe(true)
                .build();

        String responseMessage = "로그인 성공: " + loginUser.getEmail();
        return ResponseEntity.ok(responseMessage);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 4defbf2 (커밋 메시지)

>>>>>>> c066573 (Resolve merge conflicts before rebase)
    // 소셜 로그인
    @PostMapping("/login/social")
    public ResponseEntity<String> loginWithSocial(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 소셜 로그인 데이터 생성
        UserLoginDto socialLoginUser = UserLoginDto.builder()
                .email("socialuser@example.com")
                .rememberMe(false)
                .build();

        String responseMessage = "소셜 로그인 성공: " + socialLoginUser.getEmail();
        return ResponseEntity.ok(responseMessage);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 4defbf2 (커밋 메시지)

>>>>>>> c066573 (Resolve merge conflicts before rebase)
    // 2단계 인증
    @PostMapping("/login/2fa")
    public ResponseEntity<String> twoFactorAuthentication(@RequestBody UserLoginDto userLoginDto) {
        // 임의의 2단계 인증 데이터 생성
        UserLoginDto twoFaUser = UserLoginDto.builder()
                .email("2fauser@example.com")
                .twoFactorAuthCode("123456")
                .build();

        String responseMessage = "2단계 인증 성공: " + twoFaUser.getEmail();
        return ResponseEntity.ok(responseMessage);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 4defbf2 (커밋 메시지)

>>>>>>> c066573 (Resolve merge conflicts before rebase)
    // 비밀번호 재설정
    @PostMapping("/password/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        // 임의의 비밀번호 재설정 데이터 생성
        PasswordChangeDto resetPasswordData = PasswordChangeDto.builder()
                .currentPassword("currentPassword123")
                .newPassword("newPassword456")
                .build();

        String responseMessage = "비밀번호 재설정 성공: " + resetPasswordData.getNewPassword();
        return ResponseEntity.ok(responseMessage);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 4defbf2 (커밋 메시지)

>>>>>>> c066573 (Resolve merge conflicts before rebase)
    // 프로필 조회
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile() {
        // 임의의 유저 프로필 데이터 생성
        UserProfileDto userProfile = UserProfileDto.builder()
                .username("sampleUser")
                .email("user@example.com")
                .bio("Sample bio")
                .location("Sample location")
                .build();

        return ResponseEntity.ok(userProfile);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 4defbf2 (커밋 메시지)

>>>>>>> c066573 (Resolve merge conflicts before rebase)
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
        List<String> achievements = Arrays.asList("Achievement 1", "Achievement 2", "Achievement 3");
        return ResponseEntity.ok(achievements);
    }
<<<<<<< HEAD
=======

<<<<<<< HEAD
=======
    @PostMapping("/profile/avatar")
    public ResponseEntity<String> uploadAvatar() {
        return ResponseEntity.ok(userService.uploadAvatar());
    }

    @GetMapping("/profile/achievements")
    public ResponseEntity<String> getAchievements() {
        return ResponseEntity.ok(userService.getAchievements());
    }
>>>>>>> 1d47c3a (UPDATE : Controller 스켈레톤 코드 수정 중)
>>>>>>> c066573 (Resolve merge conflicts before rebase)
=======
>>>>>>> 4defbf2 (커밋 메시지)
}
