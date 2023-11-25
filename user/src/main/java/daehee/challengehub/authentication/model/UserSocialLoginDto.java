package daehee.challengehub.authentication.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSocialLoginDto {
    private final String provider; // 소셜 미디어 제공자 (예: "Google", "Facebook")
    private final String token;    // 소셜 미디어에서 받은 토큰
}
