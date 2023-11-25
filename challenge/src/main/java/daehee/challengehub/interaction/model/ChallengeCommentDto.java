package daehee.challengehub.interaction.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeCommentDto {
    private final Long commentId;
    private final Long challengeId;
    private final Long userId;
    private final String commentText;
    private final String postedAt;
}
