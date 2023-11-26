package daehee.challengehub.challenge.management.model;

import lombok.Data;

@Data
public class UpdateChallengeResponseDto {
    private final String message;
    private final ChallengeDto updatedChallenge;
}
