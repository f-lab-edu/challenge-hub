package daehee.challengehub.network.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowRequestDto {
    private final Long requestId; // 팔로우 요청 ID
    private final Long requesterId; // 요청자 ID
    private final Long requestedId; // 요청 대상 ID
    private final String requestDate; // 요청 날짜
    private final String requesterUsername; // 요청자 사용자명
    private final String requesterProfileImage; // 요청자 프로필 이미지 URL
}
