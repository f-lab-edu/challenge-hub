package daehee.challengehub.challenge.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeCommentDto {
    private final Long commentId;
    private final Long challengeId;
    private final Long userId;
    private final String commentText;
    private final Instant postedAt;
}
