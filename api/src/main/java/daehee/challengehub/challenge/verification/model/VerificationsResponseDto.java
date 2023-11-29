package daehee.challengehub.challenge.verification.model;

import lombok.Data;

import java.util.List;

@Data
public class VerificationsResponseDto {
    private final List<ChallengeVerificationDto> verifications;
}
