package daehee.challengehub.challenge.management.repository;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@Repository
public class ManagementRepository {
    private final Map<Long, ChallengeDto> challenges = new HashMap<>();
    private final Map<Long, ChallengeTagDto> challengeTags = new HashMap<>();
    private final Map<Long, ChallengeImageDto> challengeImages = new HashMap<>();
    private long challengeIdCounter = 1;
    private long tagIdCounter = 1;
    private long imageIdCounter = 1;

    public ManagementRepository() {
        // 초기 챌린지 데이터 설정
        ChallengeDto challenge1 = ChallengeDto.builder()
                .challengeId(challengeIdCounter++)
                .title("30일 요가 도전")
                .description("초보자를 위한 매일 요가 도전.")
                .tags(List.of("요가", "건강"))
                .imageUrls(List.of("https://example.com/image1.jpg"))
                .startDate(Instant.parse("2023-11-01T09:30:00Z"))
                .endDate(Instant.parse("2023-11-30T23:59:00Z"))
                .createdBy("사용자1")
                .createdAt(Instant.parse("2023-10-15T08:00:00Z"))
                .lastModified(Instant.parse("2023-11-01T10:00:00Z"))
                .build();
        challenges.put(challenge1.getChallengeId(), challenge1);

        ChallengeDto challenge2 = ChallengeDto.builder()
                .challengeId(challengeIdCounter++)
                .title("매일 명상하기")
                .description("매일 15분간 명상하기.")
                .tags(List.of("명상", "마음챙김"))
                .imageUrls(List.of("https://example.com/image2.jpg"))
                .startDate(Instant.parse("2023-11-05T12:00:00Z"))
                .endDate(Instant.parse("2023-12-05T20:00:00Z"))
                .createdBy("사용자2")
                .createdAt(Instant.parse("2023-10-20T14:30:00Z"))
                .lastModified(Instant.parse("2023-11-04T17:45:00Z"))
                .build();
        challenges.put(challenge2.getChallengeId(), challenge2);

        // 초기 태그 데이터 (예시 데이터, 실제 연결 로직 필요)
        ChallengeTagDto tag1 = ChallengeTagDto.builder()
                .tagId(tagIdCounter++)
                .tagName("새로운 태그")
                .build();
        challengeTags.put(tag1.getTagId(), tag1);

        // 초기 이미지 데이터 (예시 데이터, 실제 연결 로직 필요)
        ChallengeImageDto image1 = ChallengeImageDto.builder()
                .imageId(imageIdCounter++)
                .imageUrl("https://example.com/new_image.jpg")
                .build();
        challengeImages.put(image1.getImageId(), image1);
    }

    public void createChallenge(ChallengeDto challengeData) {
        ChallengeDto newChallenge = ChallengeDto.builder()
                .challengeId(challengeIdCounter++)
                .title(challengeData.getTitle())
                .description(challengeData.getDescription())
                .tags(challengeData.getTags())
                .imageUrls(challengeData.getImageUrls())
                .startDate(challengeData.getStartDate())
                .endDate(challengeData.getEndDate())
                .createdBy(challengeData.getCreatedBy())
                .createdAt(challengeData.getCreatedAt())
                .lastModified(challengeData.getLastModified())
                .build();
        challenges.put(newChallenge.getChallengeId(), newChallenge);
    }

    public ChallengeDto getChallengeById(Long challengeId) {
        return challenges.get(challengeId);
    }

    public List<ChallengeDto> getAllChallenges() {
         return List.of(challenges.values().toArray(new ChallengeDto[0]));
    }

    public void updateChallenge(Long challengeId, ChallengeDto challengeData) {
        challenges.put(challengeId, challengeData);
    }

    public void deleteChallenge(Long challengeId) {
        challenges.remove(challengeId);
    }

    public void addTagToChallenge(Long challengeId, ChallengeTagDto tagData) {
        ChallengeTagDto newTag = ChallengeTagDto.builder()
                .tagId(tagIdCounter++)
                .tagName(tagData.getTagName())
                .build();
        challengeTags.put(newTag.getTagId(), newTag);
        // TODO: 챌린지에 태그 연결 로직 (추가 필요)
    }

    public void removeTagFromChallenge(Long challengeId, Long tagId) {
        challengeTags.remove(tagId);
        // TODO: 챌린지에서 태그 연결 해제 로직 (추가 필요)
    }

    public void uploadImageToChallenge(Long challengeId, ChallengeImageDto imageData) {
        ChallengeImageDto newImage = ChallengeImageDto.builder()
                .imageId(imageIdCounter++)
                .imageUrl(imageData.getImageUrl())
                .build();
        challengeImages.put(newImage.getImageId(), newImage);
        // TODO: 챌린지에 이미지 연결 로직 (추가 필요)
    }

    public void removeImageFromChallenge(Long challengeId, Long imageId) {
        challengeImages.remove(imageId);
        // TODO: 챌린지에서 이미지 연결 해제 로직 (추가 필요)
    }

}
