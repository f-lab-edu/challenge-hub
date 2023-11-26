package daehee.challengehub.challenge.management.model;

import lombok.Data;

import java.util.List;

@Data
public class GetAllChallengesResponseDto {
    private final List<ChallengeDto> challenges;
}
