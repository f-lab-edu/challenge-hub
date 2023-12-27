package daehee.challengehub.challenge.management.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ParticipantDTO {
    private String id;
    private String challengeId;
    private String userId;
    private Instant joinDate;
}
