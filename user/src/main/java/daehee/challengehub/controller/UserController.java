package daehee.challengehub.controller;

import daehee.challengehub.authentication.model.PasswordChangeDto;
import daehee.challengehub.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    // 프로필 조회
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        // 성공적인 프로필 조회 시나리오
        UserProfileDto successfulProfile = UserProfileDto.builder()
                .username("sampleUser")
                .email("user@example.com")
                .bio("Sample bio")
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
