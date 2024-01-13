package daehee.challengehub.challenge.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChallengeDto {
    private String challengeId;
    private String title;
    private String frequency;
    private String duration;
    private Instant startTime;
    private Instant endTime;
    private Instant startDate;
    private String verificationMethod;
    private List<String> verificationExampleUrls;
    private Boolean isCameraOnly;
    private String description;
    private String category;
    private String coverImageUrl;
    private List<String> keywords;
    private Boolean isPublic;
    private String createdBy;
    private Instant createdAt;
    private Instant lastModified;
}
