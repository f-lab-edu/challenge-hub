package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AchievementDto {
    private final Long userId; // 사용자 ID
    private final Long challengeId; // 챌린지 ID
    private final String achievementDetails; // 성취 내용
    private final String achievedDate; // 성취한 날짜
    private final boolean isVerified; // 성취 인증 여부
}
