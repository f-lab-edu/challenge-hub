package daehee.challengehub.verification.service;

import daehee.challengehub.verification.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.verification.model.ChallengeVerificationDto;

import java.util.Arrays;
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
    public Map<String, Object> getVerifications(Long challengeId) {
        List<ChallengeVerificationDto> verifications = verificationRepository.getVerificationsByChallengeId(challengeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 내역 조회 성공");
        response.put("verifications", verifications);
        return response;
    }

    // 챌린지 인증 수정 로직
    public Map<String, Object> updateVerification(Long challengeId, Long verificationId) {
        ChallengeVerificationDto updatedVerification = ...; // 이곳에서 수정된 인증 정보 설정

        verificationRepository.updateVerification(updatedVerification);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 수정 성공");
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

    // 추가 필요한 메서드들...
}
