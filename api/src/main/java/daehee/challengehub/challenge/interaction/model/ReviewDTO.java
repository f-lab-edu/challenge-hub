package daehee.challengehub.challenge.interaction.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ReviewDTO {
    private String id;
    private String challengeId;
    private String authorId;
    private int rating;
    private String comment;
    private Instant createdDate;
}
