package daehee.challengehub.profile.repository;

import daehee.challengehub.profile.model.AchievementDto;
import daehee.challengehub.profile.model.UserProfileDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileRepository {
    private final Map<Long, UserProfileDto> profiles = new HashMap<>();
    private final Map<Long, List<AchievementDto>> achievements = new HashMap<>();

    // 프로필 저장
    public void saveProfile(UserProfileDto userProfile) {
        // TODO: UserId를 인덱스 값으로 할 것인데 인덱스 관련된 부분 처리 후 주석 해제
//        profiles.put(userProfile.getUserId(), userProfile);
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
