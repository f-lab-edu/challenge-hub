package daehee.challengehub.challenge.interaction.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Builder
@Getter
public class ChatMessage {
    @Id
    private String id;
    private String chatRoomId;
    private String senderId;
    private String content;
    private Instant timestamp;
}
