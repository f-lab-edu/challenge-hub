package daehee.challengehub.challenge.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
    private final String startDate;
    private final String endDate;
    private final String createdBy;
    private final String createdAt;
    private final String lastModified;
}
