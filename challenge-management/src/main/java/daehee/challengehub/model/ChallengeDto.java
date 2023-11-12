package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeDto {
    private final String title;
    private final String description;
    private final String startDate;
    private final String endDate;
    private final String category; // 챌린지 카테고리
    private final String difficulty; // 챌린지 난이도
    private final boolean isPublic; // 챌린지 공개 여부
    private final String createdBy; // 챌린지 생성자
}
