package daehee.challengehub.challenge.verification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeVerificationDto {
    private final Long verificationId;
    private final Long challengeId;
    private final Long userId;
    private final String verificationText;
    private final List<String> imageUrls;
    private final String submittedAt;
}
