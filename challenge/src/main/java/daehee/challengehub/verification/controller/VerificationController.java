package daehee.challengehub.verification.controller;

import daehee.challengehub.verification.model.ChallengeVerificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/challenges/{id}/verification")
public class VerificationController {
    // 챌린지 인증 업로드
    @PostMapping
    public ResponseEntity<ChallengeVerificationDto> uploadVerification(@PathVariable Long id, @RequestBody ChallengeVerificationDto verificationData) {
        ChallengeVerificationDto newVerification = ChallengeVerificationDto.builder()
                .verificationId(1L)
                .challengeId(3L)
                .userId(123L)
                .verificationText("이번 주 도전 성공!")
                .imageUrls(Arrays.asList("https://example.com/image.jpg"))
                .submittedAt("2023-11-15")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newVerification);
    }

    // 챌린지 인증 내역 조회
    @GetMapping
    public ResponseEntity<List<ChallengeVerificationDto>> getVerifications(@PathVariable Long id) {
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

        return ResponseEntity.ok(verifications);
    }

    // 챌린지 인증 수정
    @PutMapping("/{verificationId}")
    public ResponseEntity<ChallengeVerificationDto> updateVerification(@PathVariable Long id, @PathVariable Long verificationId) {
        ChallengeVerificationDto updatedVerification = ChallengeVerificationDto.builder()
                .verificationId(1L)
                .challengeId(3L)
                .userId(789L)
                .verificationText("챌린지 인증 수정됨")
                .imageUrls(Arrays.asList("https://example.com/updated-verification.jpg"))
                .submittedAt("2023-11-16")
                .build();

        return ResponseEntity.ok(updatedVerification);
    }

    // 챌린지 인증 삭제
    @DeleteMapping("/{verificationId}")
    public ResponseEntity<String> deleteVerification(@PathVariable Long id, @PathVariable Long verificationId) {
        verificationId = 1L;
        return ResponseEntity.ok("챌린지 인증 삭제 성공: 인증 ID " + verificationId);
    }
}
