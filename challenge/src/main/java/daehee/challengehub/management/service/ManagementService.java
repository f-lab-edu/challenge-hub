package daehee.challengehub.management.service;

import daehee.challengehub.management.model.ChallengeImageDto;
import daehee.challengehub.management.model.ChallengeTagDto;
import daehee.challengehub.management.repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.management.model.ChallengeDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagementService {

    private final ManagementRepository managementRepository;

    @Autowired
    public ManagementService(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    public Map<String, Object> createChallenge(ChallengeDto challengeData) {
        managementRepository.createChallenge(challengeData);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 생성 성공");
        response.put("createdChallenge", challengeData);
        return response;
    }

    public Map<String, Object> getAllChallenges() {
        List<ChallengeDto> challenges = managementRepository.getAllChallenges();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 목록 조회 성공");
        response.put("challenges", challenges);
        return response;
    }

    public Map<String, Object> getChallengeById(Long challengeId) {
        ChallengeDto challenge = managementRepository.getChallengeById(challengeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 상세 조회 성공");
        response.put("challenge", challenge);
        return response;
    }

    public Map<String, Object> updateChallenge(Long challengeId, ChallengeDto challengeData) {
        managementRepository.updateChallenge(challengeId, challengeData);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 수정 성공");
        response.put("updatedChallenge", challengeData);
        return response;
    }

    public Map<String, String> deleteChallenge(Long challengeId) {
        managementRepository.deleteChallenge(challengeId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "챌린지 삭제 성공: 챌린지 ID " + challengeId);
        return response;
    }

    public Map<String, Object> addTagToChallenge(Long challengeId, ChallengeTagDto tagData) {
        managementRepository.addTagToChallenge(challengeId, tagData);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "태그 추가 성공");
        response.put("tagDetails", tagData);
        return response;
    }

    public Map<String, String> removeTagFromChallenge(Long challengeId, Long tagId) {
        managementRepository.removeTagFromChallenge(challengeId, tagId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "태그 삭제 성공: 태그 ID " + tagId + " 챌린지 ID " + challengeId);
        return response;
    }

    public Map<String, Object> uploadImageToChallenge(Long challengeId, ChallengeImageDto imageData) {
        managementRepository.uploadImageToChallenge(challengeId, imageData);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "이미지 업로드 성공");
        response.put("imageDetails", imageData);
        return response;
    }

    public Map<String, String> removeImageFromChallenge(Long challengeId, Long imageId) {
        managementRepository.removeImageFromChallenge(challengeId, imageId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "이미지 삭제 성공: 이미지 ID " + imageId + " 챌린지 ID " + challengeId);
        return response;
    }

    public Map<String, String> participateInChallenge(Long challengeId) {
        // TODO: 참가자 데이터 처리 로직 구현 필요
        Map<String, String> response = new HashMap<>();
        response.put("message", "챌린지 참여 성공: 챌린지 ID " + challengeId);
        return response;
    }
}

