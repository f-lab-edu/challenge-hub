package daehee.challengehub.management.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeTagDto {
    private final Long tagId;
    private final String tagName;
}
