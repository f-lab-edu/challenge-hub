package daehee.challengehub.challenge.verification.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public class VerificationDto {
    private String id;
    private String challengeId;
    private String participantId;
    private String imageUrl;
    private Instant timestamp;
    private boolean isVerified;
}
