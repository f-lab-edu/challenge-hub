package daehee.challengehub.social.community.model;

import lombok.Data;

@Data
public class CreatePostResponseDto {
    private final String message;
    private final CommunityPostDto post;
}
