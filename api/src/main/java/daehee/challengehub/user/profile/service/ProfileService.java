package daehee.challengehub.user.profile.service;

import daehee.challengehub.common.constants.ErrorCode;
import daehee.challengehub.common.exception.CustomException;
import daehee.challengehub.user.profile.model.AchievementDto;
import daehee.challengehub.user.profile.model.AchievementsResponseDto;
import daehee.challengehub.user.profile.model.ChangePasswordResponseDto;
import daehee.challengehub.user.profile.model.PasswordChangeDto;
import daehee.challengehub.user.profile.model.ProfileResponseDto;
import daehee.challengehub.user.profile.model.UpdateProfileResponseDto;
import daehee.challengehub.user.profile.model.UserProfileDto;
import daehee.challengehub.user.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final Long loggedInUserId = 123L; // 현재 로그인한 사용자 ID (임시로 설정)

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // 사용자 프로필 조회 로직
    public ProfileResponseDto getProfile(Long userId) {
        UserProfileDto userProfile = profileRepository.findProfileByUserId(userId);
        if (userProfile != null) {
            return new ProfileResponseDto("프로필 조회 성공", userProfile);
        } else {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    // 프로필 정보 업데이트 로직
    public UpdateProfileResponseDto updateProfile(UserProfileDto userProfileDto) {
        profileRepository.updateProfile(userProfileDto.getUserId(), userProfileDto);
        return new UpdateProfileResponseDto("프로필 업데이트 성공");
    }

    // 비밀번호 변경 로직
    public ChangePasswordResponseDto changePassword(PasswordChangeDto passwordChangeDto) {
        // TODO: AuthenticationService에 구현한 로직과 중복되므로 검토 후 하나를 제거
        return new ChangePasswordResponseDto("비밀번호 변경 성공");
    }

    // 성과 목록 조회 로직
    public AchievementsResponseDto getAchievements(Long userId) {
        List<AchievementDto> userAchievements = profileRepository.findAchievementsByUserId(userId);
        return new AchievementsResponseDto(userAchievements);
    }
}

