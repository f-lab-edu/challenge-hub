package daehee.challengehub.user.message.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MessageDto {
    private final Long messageId; // 메시지 ID
    private final Long senderId; // 보내는 사람 ID
    private final Long receiverId; // 받는 사람 ID
    private final String messageContent; // 메시지 내용
    private final String sentTime; // 보낸 시간 (ISO 8601 형식)
    private final Boolean isRead; // 읽음 여부
    private final String imageUrl; // 이미지 URL (null 가능)
    private final String videoUrl; // 동영상 URL (null 가능)
    private final String fileType; // 파일 유형 (예: "image", "video", "text")
}


