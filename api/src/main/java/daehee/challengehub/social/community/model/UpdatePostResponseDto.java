package daehee.challengehub.social.community.model;

import lombok.Data;

@Data
public class UpdatePostResponseDto {
    private final String message;
    private final CommunityPostDto updatedPost;
}
