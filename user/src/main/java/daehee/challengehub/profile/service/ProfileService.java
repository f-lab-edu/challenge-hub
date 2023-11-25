package daehee.challengehub.profile.service;

import daehee.challengehub.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.constants.ErrorCode;
import daehee.challengehub.profile.model.AchievementDto;
import daehee.challengehub.profile.model.PasswordChangeDto;
import daehee.challengehub.profile.model.UserProfileDto;
import daehee.challengehub.exception.CustomException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final Long loggedInUserId = 123L; // 현재 로그인한 사용자 ID (임시로 설정)

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // 사용자 프로필 조회 로직
    public Map<String, Object> getProfile(Long userId) {
        UserProfileDto userProfile = profileRepository.findProfileByUserId(userId);
        if (userProfile != null) {
            Map<String, Object> response = new HashMap<>();
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
        profileRepository.updateProfile(userProfileDto.getUserId(), userProfileDto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "프로필 업데이트 성공");
        return response;
    }

    // 비밀번호 변경 로직
    public Map<String, String> changePassword(PasswordChangeDto passwordChangeDto) {
        // TODO: AuthenticationService에 구현한 로직과 중복되므로 검토 후 하나를 제거
        Map<String, String> response = new HashMap<>();
        response.put("message", "비밀번호 변경 성공");
        return response;
    }

    // 성과 목록 조회 로직
    public Map<String, Object> getAchievements(Long userId) {
        List<AchievementDto> userAchievements = profileRepository.findAchievementsByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "성과 목록 조회 성공");
        response.put("achievements", userAchievements);
        return response;
    }
}
