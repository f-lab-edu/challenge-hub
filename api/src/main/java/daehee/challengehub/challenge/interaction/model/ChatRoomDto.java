package daehee.challengehub.challenge.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatRoomDto {
    private String challengeId; // // 채팅 룸 Id = 챌린지 Id
    private Integer participantCount;
}
