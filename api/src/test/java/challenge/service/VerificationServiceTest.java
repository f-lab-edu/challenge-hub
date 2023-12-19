package challenge.service;


import daehee.challengehub.challenge.verification.model.ChallengeVerificationDto;
import daehee.challengehub.challenge.verification.model.DeleteVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.UpdateVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.UploadVerificationResponseDto;
import daehee.challengehub.challenge.verification.model.VerificationsResponseDto;
import daehee.challengehub.challenge.verification.repository.VerificationRepository;
import daehee.challengehub.challenge.verification.service.VerificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
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
                .submittedAt(Instant.parse("2023-11-15T13:00:00Z"))
                .build();
        doNothing().when(verificationRepository).saveVerification(any(ChallengeVerificationDto.class));

        // When
        UploadVerificationResponseDto response = verificationService.uploadVerification(challengeId, newVerificationData);

        // Then
        assertEquals("챌린지 인증 업로드 성공", response.getMessage());
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
                        .submittedAt(Instant.parse("2023-11-08T13:00:00Z"))
                        .build(),
                ChallengeVerificationDto.builder()
                        .verificationId(2L)
                        .challengeId(challengeId)
                        .userId(456L)
                        .verificationText("인증 내용 2")
                        .imageUrls(List.of("https://example.com/image2.jpg"))
                        .submittedAt(Instant.parse("2023-11-15T13:00:00Z"))
                        .build()
        );
        when(verificationRepository.getVerificationsByChallengeId(challengeId)).thenReturn(mockVerifications);

        // When
        VerificationsResponseDto response = verificationService.getVerifications(challengeId);

        // Then
//        assertEquals("챌린지 인증 내역 조회 성공", response.getMessage());
        assertEquals(mockVerifications, response.getVerifications());
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
                .submittedAt(Instant.parse("2023-11-15T13:00:00Z"))
                .build();
        when(verificationRepository.getVerificationById(verificationId)).thenReturn(existingVerification);

        // When
        UpdateVerificationResponseDto response = verificationService.updateVerification(challengeId, verificationId, newVerificationData);

        // Then
        assertEquals("챌린지 인증 업데이트 성공", response.getMessage());
        verify(verificationRepository).updateVerification(any(ChallengeVerificationDto.class));
    }

    @Test
    public void deleteVerification_DeletesVerification() {
        // Given
        Long verificationId = 1L;
        doNothing().when(verificationRepository).deleteVerification(verificationId);

        // When
        DeleteVerificationResponseDto response = verificationService.deleteVerification(verificationId);

        // Then
        assertEquals("챌린지 인증 삭제 성공: 인증 ID " + verificationId, response.getMessage());
        verify(verificationRepository).deleteVerification(verificationId);
    }
}
