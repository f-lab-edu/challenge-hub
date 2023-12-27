package daehee.challengehub.challenge.management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Participant {
    @Id
    private String id;
    private String challengeId;
    private String userId; // 참가자 사용자 ID
    private Instant joinDate; // 참가 날짜

    // TODO: 챌린지에 참여한 사용자와 관련된 추가적인 정보들이 들어갈 수도 있을 거 같다.
}
