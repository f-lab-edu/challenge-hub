package daehee.challengehub.social.community.model;

import lombok.Data;

@Data
public class LikePostResponseDto {
    private final String message;
    private final int newLikeCount;
}
