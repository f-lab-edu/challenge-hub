package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeImageDto {
    private final Long challengeId; // 챌린지 ID
    private final String imageUrl; // 이미지 URL
    private final String description; // 이미지 설명
}
