package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChallengeTagDto {
    private final Long challengeId; // 챌린지 ID
    private final String tag; // 태그 내용
    private final String tagDescription; // 태그에 대한 설명
}
