package daehee.challengehub.challenge.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewDto {
    private String reviewId;
    private String challengeId;
    private String authorId;
    private Integer rating;
    private String comment;
    private Instant createdDate;
}
