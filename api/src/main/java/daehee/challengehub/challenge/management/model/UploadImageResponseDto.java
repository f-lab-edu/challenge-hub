package daehee.challengehub.challenge.management.model;

import lombok.Data;

@Data
public class UploadImageResponseDto {
    private final String message;
    private final ChallengeImageDto image;
}
