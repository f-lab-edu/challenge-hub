package daehee.challengehub.challenge.management.model;

import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class ChallengeDTO {
    private String id;
    private String title;
    private String frequency;
    private String duration;
    private Instant startTime;
    private Instant endTime;
    private Instant startDate;
    private String verificationMethod;
    private List<String> verificationExampleUrls;
    private boolean isCameraOnly;
    private String description;
    private String category;
    private String coverImageUrl;
    private List<String> keywords;
    private boolean isPublic;
}
