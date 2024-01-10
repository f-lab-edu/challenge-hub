package daehee.challengehub.challenge.management.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ParticipantDto {
    private String participantId;
    private String challengeId;
    private String userId;
    private Instant joinDate;
    private double achievementRate; // 달성율
}

