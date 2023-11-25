package daehee.challengehub.social.network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FollowersDto {
    private final Long userId; // 사용자 ID
    private final String username; // 사용자명
    private final String profileImage; // 프로필 이미지 URL
    private final boolean isFollowedBack; // 상호 팔로우 여부
}
