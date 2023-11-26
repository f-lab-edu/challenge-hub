package daehee.challengehub.challenge.interaction.model;

import lombok.Data;

import java.util.List;

@Data
public class CommentsResponseDto {
    private final List<ChallengeCommentDto> comments;
}
