import daehee.challengehub.verification.model.ChallengeVerificationDto;
import daehee.challengehub.verification.repository.VerificationRepository;
import daehee.challengehub.verification.service.VerificationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VerificationServiceTest {

    @Mock
    private VerificationRepository verificationRepository;

    @InjectMocks
    private VerificationService verificationService;

    @Test
    public void uploadVerification_UploadsNewVerification() {
        // Given
        Long challengeId = 1L;
        ChallengeVerificationDto newVerificationData = ChallengeVerificationDto.builder()
                .challengeId(challengeId)
                .userId(123L)
                .verificationText("새 인증 내용")
                .imageUrls(List.of("https://example.com/image.jpg"))
                .submittedAt("2023-11-15")
                .build();

        // When
        Map<String, Object> response = verificationService.uploadVerification(challengeId, newVerificationData);

        // Then
        assertEquals("챌린지 인증 업로드 성공", response.get("message"));
        verify(verificationRepository).saveVerification(any(ChallengeVerificationDto.class));
    }

    @Test
    public void getVerifications_ReturnsVerificationsForChallenge() {
        // Given
        Long challengeId = 1L;
        List<ChallengeVerificationDto> mockVerifications = Arrays.asList(
                ChallengeVerificationDto.builder()
                        .verificationId(1L)
                        .challengeId(challengeId)
                        .userId(123L)
                        .verificationText("인증 내용 1")
                        .imageUrls(List.of("https://example.com/image1.jpg"))
                        .submittedAt("2023-11-08")
                        .build(),
                ChallengeVerificationDto.builder()
                        .verificationId(2L)
                        .challengeId(challengeId)
                        .userId(456L)
                        .verificationText("인증 내용 2")
                        .imageUrls(List.of("https://example.com/image2.jpg"))
                        .submittedAt("2023-11-15")
                        .build()
        );
        when(verificationRepository.getVerificationsByChallengeId(challengeId)).thenReturn(mockVerifications);

        // When
        Map<String, Object> response = verificationService.getVerifications(challengeId);

        // Then
        assertEquals("챌린지 인증 내역 조회 성공", response.get("message"));
        assertEquals(mockVerifications, response.get("verifications"));
    }

    @Test
    public void updateVerification_UpdatesExistingVerification() {
        // Given
        Long challengeId = 1L;
        Long verificationId = 2L;
        ChallengeVerificationDto newVerificationData = ChallengeVerificationDto.builder()
                .verificationText("업데이트된 인증 내용")
                .imageUrls(List.of("https://example.com/updated.jpg"))
                .build();
        ChallengeVerificationDto existingVerification = ChallengeVerificationDto.builder()
                .verificationId(verificationId)
                .challengeId(challengeId)
                .userId(789L)
                .verificationText("원래 인증 내용")
                .imageUrls(List.of("https://example.com/original.jpg"))
                .submittedAt("2023-11-15")
                .build();
        when(verificationRepository.getVerificationById(verificationId)).thenReturn(existingVerification);

        // When
        Map<String, Object> response = verificationService.updateVerification(challengeId, verificationId, newVerificationData);

        // Then
        assertEquals("챌린지 인증 업데이트 성공", response.get("message"));
        verify(verificationRepository).updateVerification(any(ChallengeVerificationDto.class));
    }

    @Test
    public void deleteVerification_DeletesVerification() {
        // Given
        Long verificationId = 1L;
        doNothing().when(verificationRepository).deleteVerification(verificationId);

        // When
        Map<String, String> response = verificationService.deleteVerification(verificationId);

        // Then
        assertEquals("챌린지 인증 삭제 성공: 인증 ID " + verificationId, response.get("message"));
        verify(verificationRepository).deleteVerification(verificationId);
    }

}
