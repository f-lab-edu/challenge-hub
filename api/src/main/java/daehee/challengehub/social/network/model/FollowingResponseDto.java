package daehee.challengehub.social.network.model;

import lombok.Data;

import java.util.List;

@Data
public class FollowingResponseDto {
    private final List<FollowDto> followings;
}
