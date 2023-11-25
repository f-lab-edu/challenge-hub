package daehee.challengehub.user.message.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatRoomDto {
    private final Long roomId; // 채팅방 ID
    private final String roomName; // 채팅방 이름
    private final Long lastMessageId; // 마지막 메시지 ID
    private final String lastMessagePreview; // 마지막 메시지 미리보기
    private final String lastMessageTime; // 마지막 메시지 시간
    private final Integer unreadMessagesCount; // 읽지 않은 메시지 수
    private final String roomImageUrl; // 채팅방 이미지 URL (옵션)
}
