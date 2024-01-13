package daehee.challengehub.challenge.management.model;

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
public class ParticipantDto {
    private String participantId;
    private String challengeId;
    private String userId;
    private Instant joinDate;
    private double achievementRate; // 달성율
}

