package daehee.challengehub.challenge.verification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VerificationDto {
    private String verificationId;
    private String challengeId;
    private String participantId;
    private String imageUrl;
    private Instant timestamp;
    private Boolean isVerified;
}
