package daehee.challengehub.challenge.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeCommentDto {
    private final Long commentId;
    private final Long challengeId;
    private final Long userId;
    private final String commentText;
    private final String postedAt;
}
