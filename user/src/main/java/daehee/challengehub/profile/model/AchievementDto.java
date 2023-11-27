package daehee.challengehub.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AchievementDto {
    private final Long userId; // 사용자 ID
    private final Long challengeId; // 챌린지 ID
    private final String achievementDetails; // 성취 내용, TODO: 애매하다 지워야 할 거 같은데?
    private final String achievedDate; // 성취한 날짜
}
