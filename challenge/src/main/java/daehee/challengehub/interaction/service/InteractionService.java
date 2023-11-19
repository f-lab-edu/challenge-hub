package daehee.challengehub.interaction.service;

import org.springframework.stereotype.Service;

import daehee.challengehub.interaction.model.ChallengeCommentDto;
import daehee.challengehub.interaction.model.ChallengeParticipantDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InteractionService {

    // 챌린지에 대한 댓글 작성 로직
    public Map<String, Object> postComment(Long id, String commentText) {
        ChallengeCommentDto newComment = ChallengeCommentDto.builder()
                .commentId(1L)
                .challengeId(3L)
                .userId(123L)
                .commentText("덕분에 즐겁게 챌린지 즐겼습니다. 감사합니다.")
                .postedAt("2023-11-15T12:00:00Z")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글 작성 성공");
        response.put("comment", newComment);
        return response;
    }

    // 챌린지 댓글 목록 조회 로직
    public Map<String, Object> getComments(Long id) {
        List<ChallengeCommentDto> comments = Arrays.asList(
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글 목록 조회 성공");
        response.put("comments", comments);
        return response;
    }

    // 챌린지별 리더보드 조회 로직
    public Map<String, Object> getLeaderboard(Long id) {
        List<ChallengeParticipantDto> leaderboard = Arrays.asList(
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "리더보드 조회 성공");
        response.put("leaderboard", leaderboard);
        return response;
    }

    // 참여자 상세 정보 조회 로직
    public Map<String, Object> getParticipantDetails(Long id) {
        List<ChallengeParticipantDto> participants = Arrays.asList(
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "참여자 상세 정보 조회 성공");
        response.put("participants", participants);
        return response;
    }

    // 참여자 관리 로직
    // TODO: 인증이 안되면 자동으로 추방하는 기능도 만들어야할 거 같다.
    public Map<String, String> manageParticipants(Long id, ChallengeParticipantDto participantData) {
        id = 1L;
        Map<String, String> response = new HashMap<>();
        response.put("message", String.format("챌린지 ID %d에 대한 참여자 관리 성공", id));
        return response;
    }
}
