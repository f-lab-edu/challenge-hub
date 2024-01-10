package daehee.challengehub.challenge.verification.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Document
public class Verification {
    @Id
    private String verificationId;
    private String challengeId;
    private String participantId; // 인증한 참가자 ID
    private String imageUrl; // 인증 이미지 URL
    private Instant timestamp; // 인증 시간
    private Boolean isVerified; // 인증 상태 (승인됨/거절됨)
}
