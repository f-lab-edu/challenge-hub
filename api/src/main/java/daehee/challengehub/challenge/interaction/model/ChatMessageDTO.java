package daehee.challengehub.challenge.interaction.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ChatMessageDTO {
    private String id;
    private String chatRoomId;
    private String senderId;
    private String content;
    private Instant timestamp;
}
