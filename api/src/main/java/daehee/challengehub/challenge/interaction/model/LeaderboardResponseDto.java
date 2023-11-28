package daehee.challengehub.challenge.interaction.model;

import lombok.Data;

import java.util.List;

@Data
public class LeaderboardResponseDto {
    private final List<ParticipantScoreDto> leaderboard; // ParticipantScoreDto는 리더보드에 필요한 데이터를 포함
}
