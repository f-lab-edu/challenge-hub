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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    // 사용자의 프로필 조회
    @GetMapping("/user/{userId}")
    public Map<String, Object> getProfile(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        boolean userExists = true; // 사용자 존재 여부
        Long loggedInUserId = 123L; // 임의의 현재 로그인한 사용자 ID

        if (userExists) {
            // 사용자의 프로필 정보 조회
            UserProfileDto userProfile = UserProfileDto.builder()
                    .username("sampleUser")
                    .nickname("SampleNickname")
                    .email("user@example.com")
                    .bio("This is a sample bio.")
                    .build();

            response.put("message", "프로필 조회 성공");
            response.put("profile", userProfile);
            response.put("isCurrentUser", userId.equals(loggedInUserId)); // 현재 로그인한 사용자인지 여부
            return response;
        } else {
            response.put("error", "프로필 조회 실패: 해당 사용자 ID로 사용자를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response).getBody();
        }
    }

    // 프로필 정보 업데이트
    @PutMapping("/user")
    public Map<String, String> updateProfile(@RequestBody UserProfileDto userProfileDto) {
        UserProfileDto updatedProfile = UserProfileDto.builder()
                .username("updatedUser")
                .nickname("UpdatedNickname")
                .email("updated@example.com")
                .bio("Updated bio")
                .build();

        Map<String, String> response = new HashMap<>();
        response.put("message", "프로필 업데이트 성공");
        response.put("updatedProfile", updatedProfile.getUsername());
        return response;
    }

    // 비밀번호 변경, TODO: 비밀번호 재설정 요청이랑 겹치는데... 지워야하나 그냥 둬야하나
    @PutMapping("/user/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        PasswordChangeDto newPasswordData = PasswordChangeDto.builder()
                .currentPassword("oldPassword")
                .newPassword("newStrongPassword")
                .build();

        Map<String, String> response = new HashMap<>();
        response.put("message", "비밀번호 변경 성공");
        response.put("newPassword", newPasswordData.getNewPassword());
        return ResponseEntity.ok(response.toString());
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

        Map<String, Object> response = new HashMap<>();
        response.put("achievements", achievements);
        return ResponseEntity.ok((List<AchievementDto>) response);
    }
}
