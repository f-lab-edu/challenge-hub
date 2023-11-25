package daehee.challengehub.user.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileDto {
    private final Long userId;   // 사용자의 고유 식별자
    private final String username;
    private final String nickname;
    private final String email;
    private final String bio;
}
