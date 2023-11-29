package daehee.challengehub.challenge.interaction.model;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantDetailsResponseDto {
    private final List<ChallengeParticipantDto> participants;
}
