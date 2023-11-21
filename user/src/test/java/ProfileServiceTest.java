import daehee.challengehub.exception.CustomException;
import daehee.challengehub.profile.model.AchievementDto;
import daehee.challengehub.profile.model.PasswordChangeDto;
import daehee.challengehub.profile.model.UserProfileDto;
import daehee.challengehub.profile.repository.ProfileRepository;
import daehee.challengehub.profile.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    public void getProfile_UserExists_ReturnsProfile() {
        Long userId = 123L;
        UserProfileDto mockProfile = new UserProfileDto(userId, "sampleUser", "SampleNickname", "user@example.com", "Sample bio");
        when(profileRepository.findProfileByUserId(userId)).thenReturn(mockProfile);

        Map<String, Object> response = profileService.getProfile(userId);

        assertEquals("프로필 조회 성공", response.get("message"));
        assertEquals(mockProfile, response.get("profile"));
        assertTrue((Boolean) response.get("isCurrentUser"));
    }

    @Test
    public void getProfile_UserNotExists_ThrowsException() {
        Long userId = 2L;
        when(profileRepository.findProfileByUserId(userId)).thenReturn(null);

        assertThrows(CustomException.class, () -> profileService.getProfile(userId));
    }

    @Test
    public void updateProfile_UpdatesProfile_ReturnsSuccessMessage() {
        UserProfileDto updatedProfile = new UserProfileDto(1L, "updatedUser", "UpdatedNickname", "updated@example.com", "Updated bio");
        doNothing().when(profileRepository).updateProfile(eq(1L), any(UserProfileDto.class));

        Map<String, String> response = profileService.updateProfile(updatedProfile);

        assertEquals("프로필 업데이트 성공", response.get("message"));
        verify(profileRepository).updateProfile(eq(1L), any(UserProfileDto.class));
    }

    @Test
    public void getAchievements_ExistingUser_ReturnsAchievements() {
        Long userId = 1L;
        List<AchievementDto> achievements = Arrays.asList(new AchievementDto(userId, 101L, "10일 연속 챌린지 완료", "2023-01-10"));
        when(profileRepository.findAchievementsByUserId(userId)).thenReturn(achievements);

        Map<String, Object> response = profileService.getAchievements(userId);

        assertEquals("성과 목록 조회 성공", response.get("message"));
        assertEquals(achievements, response.get("achievements"));
        verify(profileRepository).findAchievementsByUserId(userId);
    }

    @Test
    public void changePassword_UpdatesPassword_ReturnsSuccessMessage() {
        PasswordChangeDto passwordChangeDto = new PasswordChangeDto("oldPassword", "newPassword");

        Map<String, String> response = profileService.changePassword(passwordChangeDto);

        assertEquals("비밀번호 변경 성공", response.get("message"));
        // 여기서는 실제 Repository 호출이 없기 때문에 verify() 호출은 필요 없습니다.
    }
}
