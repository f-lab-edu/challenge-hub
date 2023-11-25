package daehee.challengehub.challenge.interaction.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeParticipantDto {
    private final Long participantId;
    private final Long challengeId;
    private final String participantUsername;
    private final String joinedAt;
}
