package daehee.challengehub.challenge.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ParticipantScoreDto {
    private final Long participantId; // 참가자의 사용자 ID
    private final String participantUsername; // 참가자의 사용자명
    private final int score; // 참가자의 점수
    private final String rank; // 참가자의 순위
    private final String participantProfileImage; // 참가자의 프로필 이미지 URL
}

