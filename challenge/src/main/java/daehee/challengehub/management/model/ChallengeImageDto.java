package daehee.challengehub.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeImageDto {
    private final Long imageId;
    private final String imageUrl;
}
