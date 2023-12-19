package daehee.challengehub.user.profile.repository;

import daehee.challengehub.user.profile.model.AchievementDto;
import daehee.challengehub.user.profile.model.UserProfileDto;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileRepository {
    private final Map<Long, UserProfileDto> profiles = new HashMap<>();
    private final Map<Long, List<AchievementDto>> achievements = new HashMap<>();

    public ProfileRepository() {
        // 초기 프로필 데이터
        UserProfileDto sampleProfile = UserProfileDto.builder()
                .userId(1L)
                .username("sampleUser")
                .nickname("SampleNickname")
                .email("user@example.com")
                .bio("This is a sample bio.")
                .build();
        profiles.put(sampleProfile.getUserId(), sampleProfile);

        // 초기 성과 데이터
        List<AchievementDto> sampleAchievements = List.of(
                AchievementDto.builder()
                        .userId(1L)
                        .challengeId(101L)
                        .achievementDetails("10일 연속 챌린지 완료")
                        .achievedDate(Instant.parse("2023-01-10T13:00:00Z"))
                        .build(),
                AchievementDto.builder()
                        .userId(1L)
                        .challengeId(102L)
                        .achievementDetails("커뮤니티에서 활발한 활동")
                        .achievedDate(Instant.parse("2023-02-05T13:00:00Z"))
                        .build()
        );
        achievements.put(1L, sampleAchievements);
    }

    // 프로필 저장
    public void saveProfile(UserProfileDto userProfile) {
        profiles.put(userProfile.getUserId(), userProfile);
    }

    // 프로필 조회
    public UserProfileDto findProfileByUserId(Long userId) {
        return profiles.get(userId);
    }

    // 프로필 업데이트
    public void updateProfile(Long userId, UserProfileDto updatedProfile) {
        profiles.put(userId, updatedProfile);
    }

    // 성과 목록 저장
    public void saveAchievements(Long userId, List<AchievementDto> userAchievements) {
        achievements.put(userId, userAchievements);
    }

    // 성과 목록 조회
    public List<AchievementDto> findAchievementsByUserId(Long userId) {
        return achievements.getOrDefault(userId, new ArrayList<>());
    }
}
