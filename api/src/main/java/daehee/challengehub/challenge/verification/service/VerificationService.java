package daehee.challengehub.challenge.verification.service;

import daehee.challengehub.challenge.verification.model.ChallengeVerificationDto;
import daehee.challengehub.challenge.verification.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VerificationService {
    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    // 챌린지 인증 업로드 로직
    public Map<String, Object> uploadVerification(Long id, ChallengeVerificationDto verificationData) {
        verificationRepository.saveVerification(verificationData);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 업로드 성공");
        response.put("newVerification", verificationData);
        return response;
    }

    // 챌린지 인증 내역 조회 로직
    public Map<String, Object> getVerifications(Long id) {
        List<ChallengeVerificationDto> verifications = verificationRepository.getVerificationsByChallengeId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 내역 조회 성공");
        response.put("verifications", verifications);
        return response;
    }

    // 챌린지 인증 수정 로직
    public Map<String, Object> updateVerification(Long challengeId, Long verificationId, ChallengeVerificationDto newVerificationData) {
        ChallengeVerificationDto existingVerification = verificationRepository.getVerificationById(verificationId);

        if (existingVerification == null) {
            return Collections.singletonMap("message", "인증 정보가 없음");
        }

        ChallengeVerificationDto updatedVerification = ChallengeVerificationDto.builder()
                .verificationId(verificationId)
                .challengeId(existingVerification.getChallengeId())
                .userId(existingVerification.getUserId())
                .verificationText(newVerificationData.getVerificationText())
                .imageUrls(newVerificationData.getImageUrls())
                .submittedAt(existingVerification.getSubmittedAt())
                .build();

        verificationRepository.updateVerification(updatedVerification);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 업데이트 성공");
        response.put("updatedVerification", updatedVerification);
        return response;
    }


    // 챌린지 인증 삭제 로직
    public Map<String, String> deleteVerification(Long verificationId) {
        verificationRepository.deleteVerification(verificationId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "챌린지 인증 삭제 성공: 인증 ID " + verificationId);
        return response;
    }
}
