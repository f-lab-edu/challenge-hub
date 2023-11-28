package daehee.challengehub.user.profile.model;

import lombok.Data;

import java.util.List;

@Data
public class AchievementsResponseDto {
    private final List<AchievementDto> achievements;
}
