package daehee.challengehub.profile.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDto {
    private final String username;
    private final String nickname;
    private final String email;
    private final String bio;
}
