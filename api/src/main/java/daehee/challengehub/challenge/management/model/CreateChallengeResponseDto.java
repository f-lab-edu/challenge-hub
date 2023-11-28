package daehee.challengehub.challenge.management.model;

import lombok.Data;

@Data
public class CreateChallengeResponseDto {
    private final String message;
    private final ChallengeDto challenge;
}
