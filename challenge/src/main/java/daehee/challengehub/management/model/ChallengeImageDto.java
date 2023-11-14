package daehee.challengehub.management.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeImageDto {
    private final Long imageId;
    private final String imageUrl;
}
