package daehee.challengehub.social.network.model;

import lombok.Data;

@Data
public class FollowResponseDto {
    private final String message;
    private final FollowDto followDetails;
}
