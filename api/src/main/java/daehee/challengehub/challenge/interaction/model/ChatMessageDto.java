package daehee.challengehub.challenge.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessageDto {
    private String chatMessageId;
    private String challengeId; // 채팅 룸 Id = 챌린지 Id
    private String senderId;
    private String content;
    private Instant timestamp;
}
