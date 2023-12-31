package daehee.challengehub.challenge.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeDto {
    private final Long challengeId;
    private final String title;
    private final String description;
    private final List<String> tags;
    private final List<String> imageUrls;
    private final Instant startDate;
    private final Instant endDate;
    private final String createdBy;
    private final Instant createdAt;
    private final Instant lastModified;
}
