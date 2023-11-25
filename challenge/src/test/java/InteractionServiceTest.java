import daehee.challengehub.interaction.model.ChallengeCommentDto;
import daehee.challengehub.interaction.model.ChallengeParticipantDto;
import daehee.challengehub.interaction.repository.InteractionRepository;
import daehee.challengehub.interaction.service.InteractionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;


@ExtendWith(MockitoExtension.class)
public class InteractionServiceTest {

    @Mock
    private InteractionRepository interactionRepository;

    @InjectMocks
    private InteractionService interactionService;

    @Test
    public void postComment_SuccessfullyPostsComment() {
        // Given
        Long challengeId = 3L;
        String commentText = "덕분에 즐겁게 챌린지 즐겼습니다. 감사합니다.";
        ChallengeCommentDto newComment = new ChallengeCommentDto(1L, challengeId, 123L, commentText, "2023-11-15T12:00:00Z");
        doNothing().when(interactionRepository).postComment(eq(challengeId), any(ChallengeCommentDto.class));

        // When
        Map<String, Object> response = interactionService.postComment(challengeId, commentText);

        // Then
        assertEquals("댓글 작성 성공", response.get("message"));
        verify(interactionRepository).postComment(eq(challengeId), any(ChallengeCommentDto.class));
    }

    @Test
    public void getComments_RetrievesCommentsSuccessfully() {
        // Given
        Long challengeId = 3L;
        List<ChallengeCommentDto> mockComments = Arrays.asList(
                ChallengeCommentDto.builder()
                        .commentId(1L)
                        .challengeId(3L)
                        .userId(123L)
                        .commentText("좋은 챌린지 였습니다.")
                        .postedAt("2023-11-15T12:00:00Z")
                        .build(),
                ChallengeCommentDto.builder()
                        .commentId(2L)
                        .challengeId(4L)
                        .userId(456L)
                        .commentText("참여하여 너무나도 즐거웠습니다.")
                        .postedAt("2023-11-15T13:00:00Z")
                        .build()
        );
        when(interactionRepository.getComments(challengeId)).thenReturn(mockComments);

        // When
        Map<String, Object> response = interactionService.getComments(challengeId);

        // Then
        assertEquals("댓글 목록 조회 성공", response.get("message"));
        assertEquals(mockComments, response.get("comments"));
    }

    @Test
    public void getLeaderboard_RetrievesLeaderboardSuccessfully() {
        // Given
        Long challengeId = 3L;
        List<ChallengeParticipantDto> mockLeaderboard = Arrays.asList(
                ChallengeParticipantDto.builder()
                        .participantId(1L)
                        .challengeId(3L)
                        .participantUsername("user1")
                        .joinedAt("2023-11-14")
                        .build(),
                ChallengeParticipantDto.builder()
                        .participantId(2L)
                        .challengeId(4L)
                        .participantUsername("user2")
                        .joinedAt("2023-11-14")
                        .build()
        );
        when(interactionRepository.getLeaderboard(challengeId)).thenReturn(mockLeaderboard);

        // When
        Map<String, Object> response = interactionService.getLeaderboard(challengeId);

        // Then
        assertEquals("리더보드 조회 성공", response.get("message"));
        assertEquals(mockLeaderboard, response.get("leaderboard"));
    }

    @Test
    public void getParticipantDetails_RetrievesParticipantDetailsSuccessfully() {
        // Given
        Long challengeId = 3L;
        List<ChallengeParticipantDto> mockParticipants = Arrays.asList(
                ChallengeParticipantDto.builder()
                        .participantId(3L)
                        .challengeId(5L)
                        .participantUsername("user3")
                        .joinedAt("2023-11-13")
                        .build(),
                ChallengeParticipantDto.builder()
                        .participantId(4L)
                        .challengeId(6L)
                        .participantUsername("user4")
                        .joinedAt("2023-11-12")
                        .build()
        );
        when(interactionRepository.getParticipants(challengeId)).thenReturn(mockParticipants);

        // When
        Map<String, Object> response = interactionService.getParticipantDetails(challengeId);

        // Then
        assertEquals("참여자 상세 정보 조회 성공", response.get("message"));
        assertEquals(mockParticipants, response.get("participants"));
    }

    // TODO: manageParticipants 메서드의 테스트 구현
}
