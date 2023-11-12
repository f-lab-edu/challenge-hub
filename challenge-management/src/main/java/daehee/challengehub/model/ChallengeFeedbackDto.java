package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeFeedbackDto {
    private final Long userId; // 피드백을 제공하는 사용자 ID
    private final Long challengeId; // 피드백이 관련된 챌린지 ID
    private final String feedback; // 피드백 내용
    private final String createdAt; // 피드백 작성 시간
}
