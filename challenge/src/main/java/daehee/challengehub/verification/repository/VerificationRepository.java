package daehee.challengehub.verification.repository;

import daehee.challengehub.verification.model.ChallengeVerificationDto;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class VerificationRepository {
    private final Map<Long, ChallengeVerificationDto> verifications = new HashMap<>();
    private long verificationIdCounter = 1;

    public VerificationRepository() {
        // 초기 챌린지 인증 데이터 설정
        ChallengeVerificationDto verification1 = ChallengeVerificationDto.builder()
                .verificationId(verificationIdCounter++)
                .challengeId(3L)
                .userId(123L)
                .verificationText("첫 번째 주 인증 완료")
                .imageUrls(Arrays.asList("https://example.com/image1.jpg"))
                .submittedAt("2023-11-08")
                .build();
        verifications.put(verification1.getVerificationId(), verification1);

        ChallengeVerificationDto verification2 = ChallengeVerificationDto.builder()
                .verificationId(verificationIdCounter++)
                .challengeId(4L)
                .userId(456L)
                .verificationText("두 번째 주 도전 성공")
                .imageUrls(Arrays.asList("https://example.com/image2.jpg"))
                .submittedAt("2023-11-15")
                .build();
        verifications.put(verification2.getVerificationId(), verification2);
    }

    public void saveVerification(ChallengeVerificationDto verification) {
        Long newVerificationId = verificationIdCounter++;
        ChallengeVerificationDto newVerification = ChallengeVerificationDto.builder()
                .verificationId(newVerificationId)
                .challengeId(verification.getChallengeId())
                .userId(verification.getUserId())
                .verificationText(verification.getVerificationText())
                .imageUrls(verification.getImageUrls())
                .submittedAt(verification.getSubmittedAt())
                .build();
        verifications.put(newVerificationId, newVerification);
    }

    public List<ChallengeVerificationDto> getVerificationsByChallengeId(Long challengeId) {
        return verifications.values().stream()
                .filter(verification -> verification.getChallengeId().equals(challengeId))
                .collect(Collectors.toList());
    }

    public void updateVerification(ChallengeVerificationDto verification) {
        verifications.put(verification.getVerificationId(), verification);
    }

    public void deleteVerification(Long verificationId) {
        verifications.remove(verificationId);
    }

    public ChallengeVerificationDto getVerificationById(Long verificationId) {
        return verifications.get(verificationId);
    }
}

