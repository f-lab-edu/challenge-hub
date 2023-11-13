package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageDto {
    private final Long senderId; // 메시지 발신자 ID
    private final Long receiverId; // 메시지 수신자 ID, 이것은 그룹 ID 또는 개인 사용자 ID가 될 수 있습니다.
    private final String messageContent; // 메시지 내용
    private final String sentTime; // 메시지 보낸 시간
    private final boolean isRead; // 메시지 읽음 여부
}
