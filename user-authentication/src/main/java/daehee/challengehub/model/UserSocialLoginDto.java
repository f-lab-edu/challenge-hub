package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSocialLoginDto {
    private final String provider; // 소셜 미디어 제공자 (예: "Google", "Facebook")
    private final String token;    // 소셜 미디어에서 받은 토큰

    // TODO : 필요한 경우 추가적인 필드를 정의, ex) 사용자의 소셜 미디어 프로필 정보
}
