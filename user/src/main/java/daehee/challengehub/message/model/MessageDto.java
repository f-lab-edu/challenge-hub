package daehee.challengehub.message.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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
<<<<<<< HEAD
=======

    // 메시지 생성자 (텍스트 메시지에 사용)
    public MessageDto(Long messageId, Long senderId, Long receiverId, String messageContent, String sentTime, Boolean isRead) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
        this.sentTime = sentTime;
        this.isRead = isRead;
        this.imageUrl = null;
        this.videoUrl = null;
        this.fileType = "text";
    }

    // 미디어 메시지 생성자 (이미지 또는 동영상에 사용)
    public MessageDto(Long messageId, Long senderId, Long receiverId, String imageUrl, String videoUrl, String fileType, String sentTime, Boolean isRead) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = null; // 미디어 메시지인 경우 텍스트 내용은 없음
        this.sentTime = sentTime;
        this.isRead = isRead;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.fileType = fileType;
    }
>>>>>>> 5090928 (ADD: MessageController.java 작성 완료)
}


