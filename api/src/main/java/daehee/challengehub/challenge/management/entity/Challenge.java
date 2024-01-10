package daehee.challengehub.challenge.management.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@Document
public class Challenge {
    @Id
    private String challengeId;
    private String title;
    private String frequency; // 인증 빈도
    private String duration; // 챌린지 기간
    private Instant startTime; // 인증 가능 시간 시작
    private Instant endTime; // 인증 가능 시간 종료
    private Instant startDate; // 챌린지 시작일
    private String verificationMethod; // 인증 방법
    private List<String> verificationExampleUrls; // 인증샷 예시 URL 리스트
    private Boolean isCameraOnly; // 카메라만 사용하는지 여부
    private String description; // 챌린지 소개
    private String category;
    private String coverImageUrl; // 대표 사진 URL
    private List<String> keywords; // 검색 키워드 리스트
    private Boolean isPublic; // 공개/비공개 챌린지
    private String createdBy; // 챌린지 생성자 ID
    private Instant createdAt; // 생성 시간
    private Instant lastModified; // 마지막 수정 시간
}
