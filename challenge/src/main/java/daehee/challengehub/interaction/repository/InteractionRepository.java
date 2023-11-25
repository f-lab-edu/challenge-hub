package daehee.challengehub.interaction.repository;

import daehee.challengehub.interaction.model.ChallengeCommentDto;
import daehee.challengehub.interaction.model.ChallengeParticipantDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InteractionRepository {
    private final Map<Long, List<ChallengeCommentDto>> challengeComments = new HashMap<>();
    private final Map<Long, List<ChallengeParticipantDto>> challengeLeaderboards = new HashMap<>();
    private final Map<Long, List<ChallengeParticipantDto>> challengeParticipants = new HashMap<>();
    private long commentIdCounter = 1;

    public InteractionRepository() {
        // 초기 댓글 데이터
        ChallengeCommentDto comment1 = ChallengeCommentDto.builder()
                .commentId(commentIdCounter++)
                .challengeId(3L)
                .userId(123L)
                .commentText("좋은 챌린지 였습니다.")
                .postedAt("2023-11-15T12:00:00Z")
                .build();
        challengeComments.computeIfAbsent(3L, k -> new ArrayList<>()).add(comment1);

        ChallengeCommentDto comment2 = ChallengeCommentDto.builder()
                .commentId(commentIdCounter++)
                .challengeId(4L)
                .userId(456L)
                .commentText("참여하여 너무나도 즐거웠습니다.")
                .postedAt("2023-11-15T13:00:00Z")
                .build();
        challengeComments.computeIfAbsent(4L, k -> new ArrayList<>()).add(comment2);

        // 초기 참여자 데이터
        ChallengeParticipantDto participant1 = ChallengeParticipantDto.builder()
                .participantId(1L)
                .challengeId(3L)
                .participantUsername("user1")
                .joinedAt("2023-11-14")
                .build();
        challengeParticipants.computeIfAbsent(3L, k -> new ArrayList<>()).add(participant1);

        ChallengeParticipantDto participant2 = ChallengeParticipantDto.builder()
                .participantId(2L)
                .challengeId(4L)
                .participantUsername("user2")
                .joinedAt("2023-11-14")
                .build();
        challengeParticipants.computeIfAbsent(4L, k -> new ArrayList<>()).add(participant2);

        // 리더보드 초기 데이터 (예시)
        ChallengeParticipantDto participant3 = ChallengeParticipantDto.builder()
                .participantId(1L)
                .challengeId(3L)
                .participantUsername("user1")
                .joinedAt("2023-11-14")
                .build();
        challengeLeaderboards.computeIfAbsent(3L, k -> new ArrayList<>()).add(participant3);

        ChallengeParticipantDto participant4 = ChallengeParticipantDto.builder()
                .participantId(2L)
                .challengeId(4L)
                .participantUsername("user2")
                .joinedAt("2023-11-14")
                .build();
        challengeLeaderboards.computeIfAbsent(4L, k -> new ArrayList<>()).add(participant4);
    }

    public void postComment(Long challengeId, ChallengeCommentDto comment) {
        ChallengeCommentDto newComment = ChallengeCommentDto.builder()
                .commentId(commentIdCounter++)
                .challengeId(comment.getChallengeId())
                .userId(comment.getUserId())
                .commentText(comment.getCommentText())
                .postedAt(comment.getPostedAt())
                .build();
        challengeComments.computeIfAbsent(challengeId, k -> new ArrayList<>()).add(newComment);
    }

    public List<ChallengeCommentDto> getComments(Long challengeId) {
        return challengeComments.getOrDefault(challengeId, new ArrayList<>());
    }

    public List<ChallengeParticipantDto> getLeaderboard(Long challengeId) {
        return challengeLeaderboards.getOrDefault(challengeId, new ArrayList<>());
    }

    public List<ChallengeParticipantDto> getParticipants(Long challengeId) {
        return challengeParticipants.getOrDefault(challengeId, new ArrayList<>());
    }

    public void manageParticipants(Long challengeId, ChallengeParticipantDto participantData) {
        // TODO: 참여자 관리 로직
    }
}

