package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LeaderboardEntryDto {
    private final Long userId;          // 사용자 ID
    private final String username;      // 사용자 이름
    private final int score;            // 사용자 점수
    private final int rank;             // 사용자 순위
}
