package daehee.challengehub.challenge.interaction.model;

import lombok.Data;

@Data
public class PostCommentResponseDto {
    private final String message;
    private final ChallengeCommentDto comment;
}
