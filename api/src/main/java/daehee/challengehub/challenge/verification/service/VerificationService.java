package daehee.challengehub.challenge.verification.service;

import daehee.challengehub.challenge.verification.entity.Verification;
import daehee.challengehub.challenge.verification.model.VerificationDto;
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

    // 인증 정보 저장
    public Verification saveVerification(VerificationDto verificationDto) {
        return verificationRepository.saveVerification(verificationDto);
    }

    // 특정 챌린지에 대한 모든 인증 정보 조회
    public List<Verification> findAllByChallengeId(String challengeId) {
        return verificationRepository.findAllByChallengeId(challengeId);
    }

    // 특정 참가자의 인증 정보 조회
    public List<Verification> findByParticipantId(String participantId) {
        return verificationRepository.findByParticipantId(participantId);
    }

    // 특정 인증 정보 조회
    public Verification findById(String id) {
        return verificationRepository.findById(id);
    }

    // 특정 인증 정보 업데이트
    public Verification updateVerification(String id, VerificationDto updatedVerificationDto) {
        return verificationRepository.updateVerification(id, updatedVerificationDto);
    }

    // 특정 인증 정보 삭제
    public boolean deleteById(String id) {
        return verificationRepository.deleteById(id);
    }
}
