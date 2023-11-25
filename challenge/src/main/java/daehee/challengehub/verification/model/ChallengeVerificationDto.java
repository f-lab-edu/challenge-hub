package daehee.challengehub.verification.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChallengeVerificationDto {
    private final Long verificationId;
    private final Long challengeId;
    private final Long userId;
    private final String verificationText;
    private final List<String> imageUrls;
    private final String submittedAt;
}
