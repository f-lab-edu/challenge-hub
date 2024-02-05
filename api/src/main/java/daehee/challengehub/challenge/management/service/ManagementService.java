package daehee.challengehub.challenge.management.service;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.entity.Participant;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.challenge.management.repository.ManagementRepository;
import daehee.challengehub.interfaces.KafkaProducerService;
import daehee.challengehub.topic.KafkaTopic;
import daehee.challengehub.util.KafkaMessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ManagementService {

    private final ManagementRepository managementRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ManagementService(ManagementRepository managementRepository, KafkaProducerService kafkaProducerService) {
        this.managementRepository = managementRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    // 챌린지 생성, v1
    public Challenge createChallengeV1(ChallengeDto challengeDto) {
        Challenge challenge = managementRepository.createChallengeV1(challengeDto);
        String notificationMessage = KafkaMessageTemplate.challengeEventNotification(challengeDto.getTitle(), "추가");
        kafkaProducerService.sendMessage(KafkaTopic.MANAGEMENT, notificationMessage);
        return challenge;
    }

    // 챌린지 생성, v2
    @Transactional
    public CompletableFuture<Challenge> createChallengeV2(ChallengeDto challengeDto) {
        return CompletableFuture.supplyAsync(() -> {
            // 챌린지 생성
            return managementRepository.createChallengeV2(challengeDto);
        }).thenApplyAsync(challenge -> {
            // Kafka 메시지 전송
            String notificationMessage = KafkaMessageTemplate.challengeEventNotification(challengeDto.getTitle(), "추가");
            kafkaProducerService.sendMessage(KafkaTopic.MANAGEMENT, notificationMessage);
            return challenge;
        });
    }


    // 전체 챌린지 목록 조회 with 커서 기반 페이지네이션
    public List<Challenge> getAllChallenges(String lastId, int limit) {
        return managementRepository.getAllChallenges(lastId, limit);
    }

    // 특정 챌린지 상세 조회
    public Challenge getChallengeById(String challengeId) {
        return managementRepository.getChallengeById(challengeId);
    }

    // 특정 챌린지 수정
    public Challenge updateChallenge(String challengeId, ChallengeDto challengeDto, boolean isFullUpdate) {
        return managementRepository.updateChallenge(challengeId, challengeDto, isFullUpdate);
    }

    // 챌린지 삭제
    public boolean deleteChallenge(String challengeId) {
        return managementRepository.deleteChallenge(challengeId);
    }

    // 챌린지 참여 신청
    public Participant participateInChallenge(String challengeId, String userId) {
        return managementRepository.participateInChallenge(challengeId, userId);
    }

    // 챌린지 참여 취소
    public boolean cancelParticipation(String challengeId, String userId) {
        return managementRepository.cancelParticipation(challengeId, userId);
    }

    // 챌린지 참가자 목록 조회, TODO: 참가자 목록 전체 조회가 말이 안된다 고치기
    public List<Participant> getChallengeParticipants(String challengeId) {
        return managementRepository.getChallengeParticipants(challengeId);
    }
}
