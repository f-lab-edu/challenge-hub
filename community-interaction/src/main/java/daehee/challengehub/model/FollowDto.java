package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowDto {
    private final Long followerId; // 팔로워의 사용자 ID
    private final Long followingId; // 팔로우 대상의 사용자 ID
    private final String followDate; // 팔로우한 날짜
    private final boolean isMutual; // 상호 팔로우 여부
}
