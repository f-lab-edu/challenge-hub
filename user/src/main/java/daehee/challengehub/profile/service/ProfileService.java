package daehee.challengehub.profile.service;

import daehee.challengehub.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.constants.ErrorCode;
import daehee.challengehub.profile.model.AchievementDto;
import daehee.challengehub.profile.model.PasswordChangeDto;
import daehee.challengehub.profile.model.UserProfileDto;
import daehee.challengehub.exception.CustomException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // 사용자 프로필 조회 로직
    public Map<String, Object> getProfile(Long userId) {
        // TODO: Repository 로직 구현이 제대로 완료되면 주석 풀 예정
//        UserProfileDto userProfile = profileRepository.findProfileByUserId(userId);
//        if (userProfile != null) {
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "프로필 조회 성공");
//            response.put("profile", userProfile);
//            // 여기서 로그인한 사용자 ID를 확인하는 로직이 필요
//            response.put("isCurrentUser", userId.equals(loggedInUserId));
//            return response;
        Map<String, Object> response = new HashMap<>();
        boolean userExists = true; // 사용자 존재 여부
        Long loggedInUserId = 123L; // 현재 로그인한 사용자 ID

        if (userExists) {
            UserProfileDto userProfile = UserProfileDto.builder()
                    .username("sampleUser")
                    .nickname("SampleNickname")
                    .email("user@example.com")
                    .bio("This is a sample bio.")
                    .build();

            response.put("message", "프로필 조회 성공");
            response.put("profile", userProfile);
            response.put("isCurrentUser", userId.equals(loggedInUserId));
            return response;
        } else {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    // 프로필 정보 업데이트 로직
    public Map<String, String> updateProfile(UserProfileDto userProfileDto) {
        // TODO: Repository 로직 구현이 제대로 완료되면 주석 풀 예정
//        profileRepository.updateProfile(userProfileDto.getUserId(), userProfileDto);
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

    // 비밀번호 변경 로직
    // TODO: AuthenticationService에 구현한 로직이랑 같은데 둘 중 하나 삭제 예정
    public Map<String, String> changePassword(PasswordChangeDto passwordChangeDto) {
        PasswordChangeDto newPasswordData = PasswordChangeDto.builder()
                .currentPassword("oldPassword")
                .newPassword("newStrongPassword")
                .build();

        Map<String, String> response = new HashMap<>();
        response.put("message", "비밀번호 변경 성공");
        response.put("newPassword", newPasswordData.getNewPassword());
        return response;
    }

    // TODO: 프로필 이미지 업로드 로직 (실제 구현 필요)

    // 성과 목록 조회 로직
    // TODO: 로직에 userId 추가할 것
//    public Map<String, Object> getAchievements(Long userId) {
    public Map<String, Object> getAchievements() {
        // TODO: Repository 로직 구현이 제대로 완료되면 주석 풀 예정
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
//        List<AchievementDto> userAchievements = profileRepository.findAchievementsByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "성과 목록 조회 성공");
        response.put("achievements", achievements);
        return response;
    }
}
