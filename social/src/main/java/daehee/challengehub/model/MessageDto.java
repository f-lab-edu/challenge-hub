package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageDto {
    private final Long senderId; // 메시지 발신자 ID
    private final Long receiverId; // 메시지 수신자 ID
    private final String messageContent; // 메시지 내용
    private final String sentTime; // 메시지 보낸 시간
    private final boolean isRead; // 메시지 읽음 여부
}
