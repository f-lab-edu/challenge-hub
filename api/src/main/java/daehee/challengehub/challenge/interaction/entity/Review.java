package daehee.challengehub.challenge.interaction.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Review {
    @Id
    private String id;
    private String challengeId;
    private String authorId;
    private int rating;
    private String comment;
    private Instant createdDate;
}
