package daehee.challengehub.challenge.verification.model;

import lombok.Data;

@Data
public class UploadVerificationResponseDto {
    private final String message;
    private final ChallengeVerificationDto verification;
}
