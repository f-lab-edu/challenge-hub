package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// TODO: 삭제될 수도 있음, 보고서 작성이 굳이 필요한건가 싶다.
public class ChallengeProgressReportDto {
    private final Long challengeId; // 챌린지 ID
    private final String progressDetails; // 진행 상태에 대한 상세 설명
    private final String reportDate; // 보고서 작성 날짜
    private final Double progressPercentage; // 챌린지 진행률 (예: 50.0%)
}
