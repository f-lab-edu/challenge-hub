package daehee.challengehub.challenge.verification.service;


import daehee.challengehub.challenge.verification.model.ChallengeVerificationDto;
import daehee.challengehub.challenge.verification.model.DeleteVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.UpdateVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.UploadVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.VerificationsResponseDto;
import daehee.challengehub.challenge.verification.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationService {
    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    // 챌린지 인증 업로드 로직
    public UploadVerificationResponseDto uploadVerification(Long id, ChallengeVerificationDto verificationData) {
        verificationRepository.saveVerification(verificationData);
        return new UploadVerificationResponseDto("챌린지 인증 업로드 성공", verificationData);
    }

    // 챌린지 인증 내역 조회 로직
    public VerificationsResponseDto getVerifications(Long id) {
        List<ChallengeVerificationDto> verifications = verificationRepository.getVerificationsByChallengeId(id);
        return new VerificationsResponseDto(verifications);
    }

    // 챌린지 인증 수정 로직
    public UpdateVerificationResponseDto updateVerification(Long challengeId, Long verificationId, ChallengeVerificationDto newVerificationData) {
        ChallengeVerificationDto existingVerification = verificationRepository.getVerificationById(verificationId);

        if (existingVerification == null) {
            return new UpdateVerificationResponseDto("인증 정보가 없음", null);
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
        return new UpdateVerificationResponseDto("챌린지 인증 업데이트 성공", updatedVerification);
    }

    // 챌린지 인증 삭제 로직
    public DeleteVerificationResponseDto deleteVerification(Long verificationId) {
        verificationRepository.deleteVerification(verificationId);
        return new DeleteVerificationResponseDto("챌린지 인증 삭제 성공: 인증 ID " + verificationId);
    }
}
