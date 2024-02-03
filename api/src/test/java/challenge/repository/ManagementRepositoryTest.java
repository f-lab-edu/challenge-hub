package challenge.repository;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.entity.Participant;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.challenge.management.repository.ManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnableAutoConfiguration
@SpringBootTest
public class ManagementRepositoryTest {

    @Autowired
    private ManagementRepository managementRepository;

    private ChallengeDto sampleChallengeDto;
    private String challengeId;

    @BeforeEach
    public void setup() {
        // 샘플 ChallengeDto 객체 생성 및 초기화
        sampleChallengeDto = ChallengeDto.builder()
                .title("새로운 챌린지")
                .frequency("매일")
                .duration("30일")
                .startTime(Instant.now())
                .endTime(Instant.now().plus(30, ChronoUnit.DAYS))
                .startDate(Instant.now())
                .verificationMethod("사진 업로드")
                .verificationExampleUrls(Arrays.asList("url1", "url2"))
                .isCameraOnly(true)
                .description("챌린지 설명")
                .category("운동")
                .coverImageUrl("image-url")
                .keywords(List.of("건강", "운동"))
                .isPublic(true)
                .createdBy("사용자ID")
                .createdAt(Instant.now())
                .lastModified(Instant.now())
                .build();
        challengeId = "임의의 챌린지 ID"; // 실제 존재하는 챌린지 ID로 대체 필요
    }

    @Test
    public void testCreateChallenge() {
        // 새로운 챌린지 생성
        Challenge createdChallenge = managementRepository.createChallengeV2(sampleChallengeDto);

        // 생성된 챌린지의 속성 검증
        assertNotNull(createdChallenge);
        assertNotNull(createdChallenge.getId());
        assertEquals(sampleChallengeDto.getTitle(), createdChallenge.getTitle());
        assertEquals(sampleChallengeDto.getFrequency(), createdChallenge.getFrequency());
        assertEquals(sampleChallengeDto.getDuration(), createdChallenge.getDuration());
        assertEquals(sampleChallengeDto.getStartTime(), createdChallenge.getStartTime());
        assertEquals(sampleChallengeDto.getEndTime(), createdChallenge.getEndTime());
        assertEquals(sampleChallengeDto.getStartDate(), createdChallenge.getStartDate());
        assertEquals(sampleChallengeDto.getVerificationMethod(), createdChallenge.getVerificationMethod());
        assertEquals(sampleChallengeDto.getVerificationExampleUrls(), createdChallenge.getVerificationExampleUrls());
        assertEquals(sampleChallengeDto.getIsCameraOnly(), createdChallenge.isCameraOnly());
        assertEquals(sampleChallengeDto.getDescription(), createdChallenge.getDescription());
        assertEquals(sampleChallengeDto.getCategory(), createdChallenge.getCategory());
        assertEquals(sampleChallengeDto.getCoverImageUrl(), createdChallenge.getCoverImageUrl());
        assertEquals(sampleChallengeDto.getKeywords(), createdChallenge.getKeywords());
        assertEquals(sampleChallengeDto.getIsPublic(), createdChallenge.isPublic());
        assertEquals(sampleChallengeDto.getCreatedBy(), createdChallenge.getCreatedBy());
        assertEquals(sampleChallengeDto.getCreatedAt(), createdChallenge.getCreatedAt());
        assertEquals(sampleChallengeDto.getLastModified(), createdChallenge.getLastModified());

        // 후속 테스트를 위한 challengeId 업데이트
        challengeId = createdChallenge.getId();
    }

    @Test
    public void testGetAllChallenges() {
        List<Challenge> challenges = managementRepository.getAllChallenges(null, 10);
        assertFalse(challenges.isEmpty());
    }

    @Test
    public void testGetChallengeById() {
        Challenge challenge = managementRepository.getChallengeById(challengeId);
        assertNotNull(challenge);
        assertEquals(challengeId, challenge.getId());
    }

    @Test
    public void testUpdateChallenge() {
        Challenge updatedChallenge = managementRepository.updateChallenge(challengeId, sampleChallengeDto, true);
        assertNotNull(updatedChallenge);
        assertEquals(sampleChallengeDto.getTitle(), updatedChallenge.getTitle());
        // 추가적인 필드 검증
    }

    @Test
    public void testDeleteChallenge() {
        boolean isDeleted = managementRepository.deleteChallenge(challengeId);
        assertTrue(isDeleted);
    }

    @Test
    public void testParticipateInChallenge() {
        String userId = "샘플 사용자 ID";
        Participant participant = managementRepository.participateInChallenge(challengeId, userId);
        assertNotNull(participant);
        assertEquals(challengeId, participant.getChallengeId());
        assertEquals(userId, participant.getUserId());
    }

    @Test
    public void testCancelParticipation() {
        String userId = "샘플 사용자 ID";
        boolean isCancelled = managementRepository.cancelParticipation(challengeId, userId);
        assertTrue(isCancelled);
    }

    @Test
    public void testGetChallengeParticipants() {
        List<Participant> participants = managementRepository.getChallengeParticipants(challengeId);
        assertFalse(participants.isEmpty());
    }

}

