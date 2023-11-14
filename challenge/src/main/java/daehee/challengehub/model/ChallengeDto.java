package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeDto {
    private final String title;
    private final String description;
    // TODO: 챌린지 진행률은 endDate에서 startDate 빼는 방식으로, 이건 클라이언트가 해주는 게 맞나?
    private final String startDate;
    private final String endDate;
    private final String category; // 챌린지 카테고리
    private final String difficulty; // 챌린지 난이도 (챌린지 생성자가 임의로 설정할 수 있음)
    private final String createdBy; // 챌린지 생성자
}
