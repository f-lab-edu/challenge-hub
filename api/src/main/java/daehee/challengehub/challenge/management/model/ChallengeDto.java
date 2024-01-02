package daehee.challengehub.challenge.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;


@Getter
@AllArgsConstructor
public class ChallengeDto {
    private final String challengeId;
    private final String title;
    private final String frequency;
    private final String duration;
    private final Instant startTime;
    private final Instant endTime;
    private final Instant startDate;
    private final String verificationMethod;
    private final List<String> verificationExampleUrls;
    private final boolean isCameraOnly;
    private final String description;
    private final String category;
    private final String coverImageUrl;
    private final List<String> keywords;
    private final boolean isPublic;
    private final String createdBy;
    private final Instant createdAt;
    private final Instant lastModified;

    // 기타 getter 및 필요한 메소드
}
