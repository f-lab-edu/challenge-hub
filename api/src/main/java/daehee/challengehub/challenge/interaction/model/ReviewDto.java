package daehee.challengehub.challenge.interaction.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ReviewDto {
    private String id;
    private String challengeId;
    private String authorId;
    private int rating;
    private String comment;
    private Instant createdDate;
}
