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
    private String chatMessageId;
    private String challengeId; // 채팅 룸 Id = 챌린지 Id
    private String senderId;
    private String content;
    private Instant timestamp;
}
