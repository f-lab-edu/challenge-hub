package user.service;

import daehee.challengehub.common.exception.CustomException;
import daehee.challengehub.user.profile.model.*;
import daehee.challengehub.user.profile.repository.ProfileRepository;
import daehee.challengehub.user.profile.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

        ProfileResponseDto response = profileService.getProfile(userId);

        assertEquals("프로필 조회 성공", response.getMessage());
        assertEquals(mockProfile, response.getUserProfile());
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

        UpdateProfileResponseDto response = profileService.updateProfile(updatedProfile);

        assertEquals("프로필 업데이트 성공", response.getMessage());
        verify(profileRepository).updateProfile(eq(1L), any(UserProfileDto.class));
    }

    @Test
    public void getAchievements_ExistingUser_ReturnsAchievements() {
        Long userId = 1L;
        List<AchievementDto> achievements = Arrays.asList(new AchievementDto(userId, 101L, "10일 연속 챌린지 완료", "2023-01-10"));
        when(profileRepository.findAchievementsByUserId(userId)).thenReturn(achievements);

        AchievementsResponseDto response = profileService.getAchievements(userId);

        assertEquals(achievements, response.getAchievements());
        verify(profileRepository).findAchievementsByUserId(userId);
    }

    @Test
    public void changePassword_UpdatesPassword_ReturnsSuccessMessage() {
        PasswordChangeDto passwordChangeDto = new PasswordChangeDto("oldPassword", "newPassword");

        ChangePasswordResponseDto response = profileService.changePassword(passwordChangeDto);

        assertEquals("비밀번호 변경 성공", response.getMessage());
    }
}

