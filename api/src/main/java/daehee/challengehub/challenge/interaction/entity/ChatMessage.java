package daehee.challengehub.challenge.interaction.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class ChatMessage {
    @Id
    private String id;
    private String chatRoomId; // TODO: challengeId와 동일, 그런데 이름은 chatRoomId로 가져가는게 맞나?
    private String senderId; // 메시지 보낸 사용자 ID
    private String content;
    private Instant timestamp;
}

