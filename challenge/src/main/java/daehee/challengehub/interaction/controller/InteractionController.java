package daehee.challengehub.interaction.controller;

import daehee.challengehub.interaction.model.ChallengeCommentDto;
import daehee.challengehub.interaction.model.ChallengeParticipantDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class InteractionController {
    // 챌린지에 대한 댓글 작성
    @PostMapping("/{id}/comments")
    public ChallengeCommentDto postComment(@PathVariable Long id, @RequestBody String commentText) {
        ChallengeCommentDto newComment = ChallengeCommentDto.builder()
                .commentId(1L)
                .challengeId(3L)
                .userId(123L)
                .commentText("덕분에 즐겁게 챌린지 즐겼습니다. 감사합니다.")
                .postedAt("2023-11-15T12:00:00Z")
                .build();

        return newComment;
    }

    // 챌린지 댓글 목록 조회
    @GetMapping("/{id}/comments")
    public List<ChallengeCommentDto> getComments(@PathVariable Long id) {
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

        return comments;
    }

    // 챌린지별 리더보드 조회
    @GetMapping("/{id}/leaderboard")
    public List<ChallengeParticipantDto> getLeaderboard(@PathVariable Long id) {
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

        return leaderboard;
    }

    // 참여자 상세 정보 조회
    @GetMapping("/{id}/participants/details")
    public List<ChallengeParticipantDto> getParticipantDetails(@PathVariable Long id) {
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

        return participants;
    }

    // 참여자 관리 (예: 참여자 추가 또는 제거)
    // TODO: 인증이 안되면 자동으로 추방하는 기능도 만들어야할 거 같다.
    @PostMapping("/{id}/participants/manage")
    public String manageParticipants(@PathVariable Long id, @RequestBody ChallengeParticipantDto participantData) {
        id = 1L;
        String responseMessage = String.format("챌린지 ID %d에 대한 참여자 관리 성공", id);
        return responseMessage;
    }
}