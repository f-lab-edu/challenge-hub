package daehee.challengehub.social.network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FollowDto {
    private final Long followerId; // 팔로워의 사용자 ID
    private final Long followingId; // 팔로우 대상의 사용자 ID
    private final String followDate; // 팔로우한 날짜
    private final boolean isMutual; // 상호 팔로우 여부
    private final String followerUsername; // 팔로워의 사용자명
    private final String followingUsername; // 팔로우 대상의 사용자명
    private final String followerProfileImage; // 팔로워의 프로필 이미지 URL
    private final String followingProfileImage; // 팔로우 대상의 프로필 이미지 URL
}
