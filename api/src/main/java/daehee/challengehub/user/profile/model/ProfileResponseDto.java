package daehee.challengehub.user.profile.model;

import lombok.Data;

@Data
public class ProfileResponseDto {
    private final String message;
    private final UserProfileDto userProfile;
}
