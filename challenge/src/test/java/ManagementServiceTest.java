
import daehee.challengehub.management.model.ChallengeDto;
import daehee.challengehub.management.model.ChallengeImageDto;
import daehee.challengehub.management.model.ChallengeTagDto;
import daehee.challengehub.management.repository.ManagementRepository;
import daehee.challengehub.management.service.ManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
public class ManagementServiceTest {

    @Mock
    private ManagementRepository managementRepository;

    @InjectMocks
    private ManagementService managementService;

    @Test
    public void createChallenge_CreatesNewChallenge() {
        // Given
        ChallengeDto challengeData = ChallengeDto.builder()
                .challengeId(1L)
                .title("Test Challenge")
                .description("Description")
                .tags(Arrays.asList("Tag1", "Tag2"))
                .imageUrls(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"))
                .startDate("2023-11-01")
                .endDate("2023-11-30")
                .createdBy("admin")
                .createdAt("2023-11-15")
                .lastModified("2023-11-15")
                .build();
        doNothing().when(managementRepository).createChallenge(any(ChallengeDto.class));

        // When
        Map<String, Object> response = managementService.createChallenge(challengeData);

        // Then
        assertEquals("챌린지 생성 성공", response.get("message"));
        assertEquals(challengeData, response.get("createdChallenge"));
        verify(managementRepository).createChallenge(any(ChallengeDto.class));
    }

    @Test
    public void getAllChallenges_ReturnsAllChallenges() {
        // Given
        List<ChallengeDto> mockChallenges = Arrays.asList(
                ChallengeDto.builder()
                        .challengeId(1L)
                        .title("Challenge 1")
                        .description("Description 1")
                        .tags(List.of("태그1"))
                        .imageUrls(List.of("https://example.com/image1.jpg"))
                        .startDate("2023-11-01")
                        .endDate("2023-11-30")
                        .createdBy("creator1")
                        .createdAt("2023-10-01")
                        .lastModified("2023-10-05")
                        .build(),
                ChallengeDto.builder()
                        .challengeId(2L)
                        .title("Challenge 2")
                        .description("Description 2")
                        .tags(List.of("태그2"))
                        .imageUrls(List.of("https://example.com/image2.jpg"))
                        .startDate("2023-11-02")
                        .endDate("2023-12-02")
                        .createdBy("creator2")
                        .createdAt("2023-10-02")
                        .lastModified("2023-10-06")
                        .build()
        );
        when(managementRepository.getAllChallenges()).thenReturn(mockChallenges);

        // When
        Map<String, Object> response = managementService.getAllChallenges();

        // Then
        assertEquals("챌린지 목록 조회 성공", response.get("message"));
        assertEquals(mockChallenges, response.get("challenges"));
    }

    @Test
    public void getChallengeById_ReturnsChallenge() {
        // Given
        Long challengeId = 1L;
        ChallengeDto mockChallenge = ChallengeDto.builder()
                .challengeId(challengeId)
                .title("Test Challenge")
                .description("Description")
                .tags(Arrays.asList("태그1", "태그2"))
                .imageUrls(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"))
                .startDate("2023-11-01")
                .endDate("2023-11-30")
                .createdBy("creator")
                .createdAt("2023-10-01")
                .lastModified("2023-10-05")
                .build();
        when(managementRepository.getChallengeById(challengeId)).thenReturn(mockChallenge);

        // When
        Map<String, Object> response = managementService.getChallengeById(challengeId);

        // Then
        assertEquals("챌린지 상세 조회 성공", response.get("message"));
        assertEquals(mockChallenge, response.get("challenge"));
    }

    @Test
    public void updateChallenge_UpdatesExistingChallenge() {
        // Given
        Long challengeId = 1L;
        ChallengeDto challengeData = ChallengeDto.builder()
                .challengeId(challengeId)
                .title("Updated Title")
                .description("Updated Description")
                .tags(Arrays.asList("수정된 태그1", "수정된 태그2"))
                .imageUrls(Arrays.asList("https://example.com/updated_image1.jpg", "https://example.com/updated_image2.jpg"))
                .startDate("2023-11-02")
                .endDate("2023-12-02")
                .createdBy("updatedCreator")
                .createdAt("2023-10-02")
                .lastModified("2023-10-06")
                .build();
        doNothing().when(managementRepository).updateChallenge(eq(challengeId), any(ChallengeDto.class));

        // When
        Map<String, Object> response = managementService.updateChallenge(challengeId, challengeData);

        // Then
        assertEquals("챌린지 수정 성공", response.get("message"));
        assertEquals(challengeData, response.get("updatedChallenge"));
        verify(managementRepository).updateChallenge(eq(challengeId), any(ChallengeDto.class));
    }

    @Test
    public void deleteChallenge_DeletesChallenge() {
        // Given
        Long challengeId = 1L;
        doNothing().when(managementRepository).deleteChallenge(challengeId);

        // When
        Map<String, String> response = managementService.deleteChallenge(challengeId);

        // Then
        assertEquals("챌린지 삭제 성공: 챌린지 ID " + challengeId, response.get("message"));
        verify(managementRepository).deleteChallenge(challengeId);
    }

    @Test
    public void addTagToChallenge_AddsTag() {
        // Given
        Long challengeId = 1L;
        ChallengeTagDto tagData = ChallengeTagDto.builder()
                .tagId(1L)
                .tagName("New Tag")
                .build();
        doNothing().when(managementRepository).addTagToChallenge(eq(challengeId), any(ChallengeTagDto.class));

        // When
        Map<String, Object> response = managementService.addTagToChallenge(challengeId, tagData);

        // Then
        assertEquals("태그 추가 성공", response.get("message"));
        assertEquals(tagData, response.get("tagDetails"));
        verify(managementRepository).addTagToChallenge(eq(challengeId), any(ChallengeTagDto.class));
    }

    @Test
    public void removeTagFromChallenge_RemovesTag() {
        // Given
        Long challengeId = 1L;
        Long tagId = 2L;
        doNothing().when(managementRepository).removeTagFromChallenge(challengeId, tagId);

        // When
        Map<String, String> response = managementService.removeTagFromChallenge(challengeId, tagId);

        // Then
        assertEquals("태그 삭제 성공: 태그 ID " + tagId + " 챌린지 ID " + challengeId, response.get("message"));
        verify(managementRepository).removeTagFromChallenge(challengeId, tagId);
    }

    @Test
    public void uploadImageToChallenge_UploadsImage() {
        // Given
        Long challengeId = 1L;
        ChallengeImageDto imageData = ChallengeImageDto.builder()
                .imageId(1L)
                .imageUrl("https://example.com/new_image.jpg")
                .build();
        doNothing().when(managementRepository).uploadImageToChallenge(eq(challengeId), any(ChallengeImageDto.class));

        // When
        Map<String, Object> response = managementService.uploadImageToChallenge(challengeId, imageData);

        // Then
        assertEquals("이미지 업로드 성공", response.get("message"));
        assertEquals(imageData, response.get("imageDetails"));
        verify(managementRepository).uploadImageToChallenge(eq(challengeId), any(ChallengeImageDto.class));
    }

    @Test
    public void removeImageFromChallenge_RemovesImage() {
        // Given
        Long challengeId = 1L;
        Long imageId = 2L;
        doNothing().when(managementRepository).removeImageFromChallenge(challengeId, imageId);

        // When
        Map<String, String> response = managementService.removeImageFromChallenge(challengeId, imageId);

        // Then
        assertEquals("이미지 삭제 성공: 이미지 ID " + imageId + " 챌린지 ID " + challengeId, response.get("message"));
        verify(managementRepository).removeImageFromChallenge(challengeId, imageId);
    }
}
