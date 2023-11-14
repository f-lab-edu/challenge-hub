package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// 챌린지에 참여한 사람들의 성실도 혹은 실행력과 관련된 부분을 평가하기 위해 만든 지표
public class LeaderboardEntryDto {
    private final Long userId;          // 사용자 ID
    private final String username;      // 사용자 이름
    private final int score;            // 사용자 점수
    private final int rank;             // 사용자 순위
}
