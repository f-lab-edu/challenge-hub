package daehee.challengehub.challenge.verification.model;

import lombok.Data;

@Data
public class UpdateVerificationResponseDto {
    private final String message;
    private final ChallengeVerificationDto updatedVerification;
}
