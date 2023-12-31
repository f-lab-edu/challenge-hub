package daehee.challengehub.challenge.verification.controller;

import daehee.challengehub.challenge.verification.model.*;
import daehee.challengehub.challenge.verification.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenges/{id}/verification")
public class VerificationController {
    private final VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping
    public UploadVerificationResponseDto uploadVerification(@PathVariable Long id, @RequestBody ChallengeVerificationDto verificationData) {
        return verificationService.uploadVerification(id, verificationData);
    }

    @GetMapping
    public VerificationsResponseDto getVerifications(@PathVariable Long id) {
        return verificationService.getVerifications(id);
    }

    @PutMapping("/{verificationId}")
    public UpdateVerificationResponseDto updateVerification(@PathVariable Long id, @PathVariable Long verificationId, @RequestBody ChallengeVerificationDto newVerificationData) {
        return verificationService.updateVerification(id, verificationId, newVerificationData);
    }

    @DeleteMapping("/{verificationId}")
    public DeleteVerificationResponseDto deleteVerification(@PathVariable Long id, @PathVariable Long verificationId) {
        return verificationService.deleteVerification(verificationId);
    }
}
