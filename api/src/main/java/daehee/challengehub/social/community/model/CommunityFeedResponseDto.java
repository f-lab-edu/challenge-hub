package daehee.challengehub.social.community.model;

import lombok.Data;

import java.util.List;

@Data
public class CommunityFeedResponseDto {
    private final List<CommunityPostDto> posts;
}
