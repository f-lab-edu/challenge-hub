package daehee.challengehub.challenge.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeTagDto {
    private final Long tagId;
    private final String tagName;
}
