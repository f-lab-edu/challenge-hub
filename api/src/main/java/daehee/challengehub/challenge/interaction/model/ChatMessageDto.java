package daehee.challengehub.challenge.interaction.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ChatMessageDto {
    private String id;
    private String chatRoomId;
    private String senderId;
    private String content;
    private Instant timestamp;
}
