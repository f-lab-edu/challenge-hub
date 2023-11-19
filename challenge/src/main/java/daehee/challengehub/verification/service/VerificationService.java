package daehee.challengehub.verification.service;

import org.springframework.stereotype.Service;

import daehee.challengehub.verification.model.ChallengeVerificationDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VerificationService {

    // 챌린지 인증 업로드 로직
    public Map<String, Object> uploadVerification(Long id, ChallengeVerificationDto verificationData) {
        ChallengeVerificationDto newVerification = ChallengeVerificationDto.builder()
                .verificationId(1L)
                .challengeId(3L)
                .userId(123L)
                .verificationText("이번 주 도전 성공!")
                .imageUrls(Arrays.asList("https://example.com/image.jpg"))
                .submittedAt("2023-11-15")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 업로드 성공");
        response.put("newVerification", newVerification);
        return response;
    }

    // 챌린지 인증 내역 조회 로직
    public Map<String, Object> getVerifications(Long id) {
        List<ChallengeVerificationDto> verifications = Arrays.asList(
                ChallengeVerificationDto.builder()
                        .verificationId(1L)
                        .challengeId(3L)
                        .userId(123L)
                        .verificationText("첫 번째 주 인증 완료")
                        .imageUrls(Arrays.asList("https://example.com/image1.jpg"))
                        .submittedAt("2023-11-08")
                        .build(),
                ChallengeVerificationDto.builder()
                        .verificationId(2L)
                        .challengeId(4L)
                        .userId(456L)
                        .verificationText("두 번째 주 도전 성공")
                        .imageUrls(Arrays.asList("https://example.com/image2.jpg"))
                        .submittedAt("2023-11-15")
                        .build()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 내역 조회 성공");
        response.put("verifications", verifications);
        return response;
    }

    // 챌린지 인증 수정 로직
    public Map<String, Object> updateVerification(Long id, Long verificationId) {
        ChallengeVerificationDto updatedVerification = ChallengeVerificationDto.builder()
                .verificationId(1L)
                .challengeId(3L)
                .userId(789L)
                .verificationText("챌린지 인증 수정됨")
                .imageUrls(Arrays.asList("https://example.com/updated-verification.jpg"))
                .submittedAt("2023-11-16")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 인증 수정 성공");
        response.put("updatedVerification", updatedVerification);
        return response;
    }

    // 챌린지 인증 삭제 로직
    public Map<String, String> deleteVerification(Long id, Long verificationId) {
        verificationId = 1L;
        Map<String, String> response = new HashMap<>();
        response.put("message", "챌린지 인증 삭제 성공: 인증 ID " + verificationId);
        return response;
    }
}
