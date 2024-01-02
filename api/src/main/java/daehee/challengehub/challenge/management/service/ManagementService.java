package daehee.challengehub.challenge.management.service;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.entity.Participant;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.challenge.management.repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementService {

    private final ManagementRepository managementRepository;

    @Autowired
    public ManagementService(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    // 챌린지 생성
    public Challenge createChallenge(ChallengeDto challengeDto) {
        return managementRepository.createChallenge(challengeDto);
    }

    // 전체 챌린지 목록 조회
    public List<Challenge> getAllChallenges() {
        return managementRepository.getAllChallenges();
    }

    // 특정 챌린지 상세 조회
    public Challenge getChallengeById(String challengeId) {
        return managementRepository.getChallengeById(challengeId);
    }

    // 특정 챌린지 수정
    public Challenge updateChallenge(String challengeId, ChallengeDto challengeDto) {
        return managementRepository.updateChallenge(challengeId, challengeDto);
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

    // 챌린지 참가자 목록 조회
    public List<Participant> getChallengeParticipants(String challengeId) {
        return managementRepository.getChallengeParticipants(challengeId);
    }
}
