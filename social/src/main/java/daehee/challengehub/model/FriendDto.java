package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendDto {
    private final Long userId; // 사용자 ID
    private final Long friendId; // 친구의 사용자 ID
    private final String friendshipStartDate; // 친구 관계 시작 날짜
}
