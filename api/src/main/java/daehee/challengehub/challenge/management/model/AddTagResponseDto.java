package daehee.challengehub.challenge.management.model;

import lombok.Data;

@Data
public class AddTagResponseDto {
    private final String message;
    private final ChallengeTagDto tag;
}
