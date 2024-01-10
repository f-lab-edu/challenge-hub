package daehee.challengehub.challenge.management.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Getter
@Document
public class Participant {
    @Id
    private String participantId;
    private String challengeId;
    private String userId;
    private Instant joinDate;
    private double achievementRate; // 참가자의 챌린지 달성율
// TODO: 챌린지에 참여한 사용자와 관련된 추가적인 정보들이 들어갈 수도 있을 거 같다.
}


