package daehee.challengehub.profile.controller;

import daehee.challengehub.profile.model.AchievementDto;
import daehee.challengehub.profile.model.PasswordChangeDto;
import daehee.challengehub.profile.model.ProfileImageUploadDto;
import daehee.challengehub.profile.model.UserProfileDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    // 현재 로그인한 사용자의 프로필 조회
    @GetMapping("/user")
    public ResponseEntity<?> getProfile() {
        // 성공적인 프로필 조회 시나리오
        UserProfileDto successfulProfile = UserProfileDto.builder()
                .username("sampleUser")
                .nickname("SampleNickname")
                .email("user@example.com")
                .bio("Sample bio")
                .build();

        // 실패한 프로필 조회 시나리오 (예: 프로필이 존재하지 않음)
        String failureMessage = "프로필 조회 실패: 사용자를 찾을 수 없습니다.";

        // 임의로 성공 또는 실패 시나리오 선택
        boolean isSuccessful = true; // 이 값을 변경하여 성공/실패 시나리오 선택
        return isSuccessful ? ResponseEntity.ok(successfulProfile) : ResponseEntity.badRequest().body(failureMessage);
    }

    // 다른 사용자의 프로필 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId) {
        boolean userExists = true;
        if (userExists) {
            // 성공 시나리오: 임의의 사용자 프로필 데이터 생성 및 반환
            UserProfileDto userProfile = UserProfileDto.builder()
                    .username("sampleUser")
                    .nickname("SampleNickname")
                    .email("user@example.com")
                    .bio("This is a sample bio.")
                    .build();
            return ResponseEntity.ok(userProfile);
        } else {
            // 실패 시나리오: 사용자가 존재하지 않음
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("프로필 조회 실패: 해당 사용자 ID로 사용자를 찾을 수 없습니다.");
        }
    }

    // 프로필 정보 업데이트
    @PutMapping("/user")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDto userProfileDto) {
        // 임의의 유저 프로필 업데이트 데이터 생성
        UserProfileDto updatedProfile = UserProfileDto.builder()
                .username("updatedUser")
                .nickname("UpdatedNickname")
                .email("updated@example.com")
                .bio("Updated bio")
                .build();

        String responseMessage = "프로필 업데이트 성공: " + updatedProfile.getUsername();
        return ResponseEntity.ok(responseMessage);
    }

    // 비밀번호 변경, TODO: 비밀번호 재설정 요청이랑 겹치는데... 지워야하나 그냥 둬야하나
    @PutMapping("/user/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        PasswordChangeDto newPasswordData = PasswordChangeDto.builder()
                .currentPassword("oldPassword")
                .newPassword("newStrongPassword")
                .build();

        String responseMessage = "비밀번호 변경 성공: " + newPasswordData.getNewPassword();
        return ResponseEntity.ok(responseMessage);
    }

    // 프로필 이미지 업로드
    // TODO: 여기는 어떻게 구현해야할 지 다시 생각해보기 -> 데이터베이스에 이미지 URL을 넣어서 가져올 수도 있고 아예 파일 업로드를 해도 되는데, 전자가 나으려나?
    @PostMapping("/user/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
//        ProfileImageUploadDto newAvatar = ProfileImageUploadDto.builder()
//                .imageFile(file)
//                .build();
//
//        String responseMessage = "프로필 이미지 업로드 성공: " + newAvatar.getImageFile().getOriginalFilename();
//        return ResponseEntity.ok(responseMessage);
        return null;
    }

    // 달성한 성과 목록 조회
    @GetMapping("/user/achievements")
    public ResponseEntity<List<AchievementDto>> getAchievements() {
        // 임의의 성과 목록 생성
        List<AchievementDto> achievements = Arrays.asList(
                AchievementDto.builder()
                        .userId(1L)
                        .challengeId(101L)
                        .achievementDetails("10일 연속 챌린지 완료")
                        .achievedDate("2023-01-10")
                        .build(),
                AchievementDto.builder()
                        .userId(1L)
                        .challengeId(102L)
                        .achievementDetails("커뮤니티에서 활발한 활동")
                        .achievedDate("2023-02-05")
                        .build(),
                AchievementDto.builder()
                        .userId(1L)
                        .challengeId(103L)
                        .achievementDetails("첫 챌린지 성공")
                        .achievedDate("2023-03-15")
                        .build(),
                AchievementDto.builder()
                        .userId(1L)
                        .challengeId(104L)
                        .achievementDetails("최다 좋아요 획득")
                        .achievedDate("2023-04-20")
                        .build()
        );

        return ResponseEntity.ok(achievements);
    }
}
