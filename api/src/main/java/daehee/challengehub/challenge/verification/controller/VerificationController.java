package daehee.challengehub.challenge.verification.controller;


import daehee.challengehub.challenge.verification.model.ChallengeVerificationDto;
import daehee.challengehub.challenge.verification.model.DeleteVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.UpdateVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.UploadVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.VerificationsResponseDto;
import daehee.challengehub.challenge.verification.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges/{id}/verification")
public class VerificationController {
    private final VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    // 챌린지 인증 정보를 업로드하는 API
    // {id}는 챌린지의 고유 ID를 나타냄
    @PostMapping
    public UploadVerificationResponseDto uploadVerification(@PathVariable Long id, @RequestBody ChallengeVerificationDto verificationData) {
        return verificationService.uploadVerification(id, verificationData);
    }

    // 특정 챌린지의 모든 인증 정보를 조회하는 API
    @GetMapping
    public VerificationsResponseDto getVerifications(@PathVariable Long id) {
        return verificationService.getVerifications(id);
    }

    // 특정 챌린지의  특정 인증 정보를 업데이트하는 API
    // {verificationId}는 인증 정보의 고유 ID
    @PutMapping("/{verificationId}")
    public UpdateVerificationResponseDto updateVerification(@PathVariable Long id, @PathVariable Long verificationId, @RequestBody ChallengeVerificationDto newVerificationData) {
        return verificationService.updateVerification(id, verificationId, newVerificationData);
    }

    // 특정 챌린지의 특정 인증 정보를 삭제하는 API
    @DeleteMapping("/{verificationId}")
    public DeleteVerificationResponseDto deleteVerification(@PathVariable Long id, @PathVariable Long verificationId) {
        return verificationService.deleteVerification(verificationId);
    }
}
