package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeParticipationDto {
    private final Long userId; // 사용자 ID
    private final Long challengeId; // 챌린지 ID
    private final String participationStatus; // 참여 상태 (예: "활성", "완료", "중단")
    private final String startDate; // 참여 시작 날짜
    private final String endDate; // 참여 종료 날짜
}
