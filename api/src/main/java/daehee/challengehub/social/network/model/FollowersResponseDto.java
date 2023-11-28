package daehee.challengehub.social.network.model;

import lombok.Data;

import java.util.List;

@Data
public class FollowersResponseDto {
    private final List<FollowersDto> followers;
}
