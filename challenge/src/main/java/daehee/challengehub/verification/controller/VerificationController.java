package daehee.challengehub.verification.controller;

import daehee.challengehub.verification.model.ChallengeVerificationDto;
import daehee.challengehub.verification.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("/challenges/{id}/verification")
public class VerificationController {
    private final VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping
    public Map<String, Object> uploadVerification(@PathVariable Long id, @RequestBody ChallengeVerificationDto verificationData) {
        return verificationService.uploadVerification(id, verificationData);
    }

    @GetMapping
    public Map<String, Object> getVerifications(@PathVariable Long id) {
        return verificationService.getVerifications(id);
    }

    @PutMapping("/{verificationId}")
    public Map<String, Object> updateVerification(@PathVariable Long id, @PathVariable Long verificationId, @RequestBody ChallengeVerificationDto newVerificationData) {
        return verificationService.updateVerification(id, verificationId, newVerificationData);
    }

    // TODO: 챌린지 id가 굳이 필요 없네 지워야하나
    @DeleteMapping("/{verificationId}")
    public Map<String, String> deleteVerification(@PathVariable Long id, @PathVariable Long verificationId) {
        return verificationService.deleteVerification(verificationId); // 인증 ID만 필요
    }
}
