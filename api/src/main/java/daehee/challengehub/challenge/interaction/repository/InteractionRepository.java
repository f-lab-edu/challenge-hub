package daehee.challengehub.challenge.interaction.repository;

import daehee.challengehub.challenge.interaction.model.ChallengeCommentDto;
import daehee.challengehub.challenge.interaction.model.ChallengeParticipantDto;
import daehee.challengehub.challenge.interaction.model.ParticipantScoreDto;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                .postedAt(Instant.parse("2023-11-15T12:00:00Z"))
                .build();
        challengeComments.computeIfAbsent(3L, k -> new ArrayList<>()).add(comment1);

        ChallengeCommentDto comment2 = ChallengeCommentDto.builder()
                .commentId(commentIdCounter++)
                .challengeId(4L)
                .userId(456L)
                .commentText("참여하여 너무나도 즐거웠습니다.")
                .postedAt(Instant.parse("2023-11-15T13:00:00Z"))
                .build();
        challengeComments.computeIfAbsent(4L, k -> new ArrayList<>()).add(comment2);

        // 초기 참여자 데이터
        ChallengeParticipantDto participant1 = ChallengeParticipantDto.builder()
                .participantId(1L)
                .challengeId(3L)
                .participantUsername("user1")
                .joinedAt(Instant.parse("2023-11-14T12:00:00Z"))
                .build();
        challengeParticipants.computeIfAbsent(3L, k -> new ArrayList<>()).add(participant1);

        ChallengeParticipantDto participant2 = ChallengeParticipantDto.builder()
                .participantId(2L)
                .challengeId(4L)
                .participantUsername("user2")
                .joinedAt(Instant.parse("2023-11-14T13:00:00Z"))
                .build();
        challengeParticipants.computeIfAbsent(4L, k -> new ArrayList<>()).add(participant2);

        // 리더보드 초기 데이터 (예시)
        ChallengeParticipantDto participant3 = ChallengeParticipantDto.builder()
                .participantId(1L)
                .challengeId(3L)
                .participantUsername("user1")
                .joinedAt(Instant.parse("2023-11-14T13:00:00Z"))
                .build();
        challengeLeaderboards.computeIfAbsent(3L, k -> new ArrayList<>()).add(participant3);

        ChallengeParticipantDto participant4 = ChallengeParticipantDto.builder()
                .participantId(2L)
                .challengeId(4L)
                .participantUsername("user2")
                .joinedAt(Instant.parse("2023-11-14T13:00:00Z"))
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

    public List<ParticipantScoreDto> getLeaderboard(Long challengeId) {
        List<ChallengeParticipantDto> participants = challengeLeaderboards.getOrDefault(challengeId, new ArrayList<>());

        // ChallengeParticipantDto 객체를 ParticipantScoreDto 객체로 변환
        return participants.stream()
                .map(this::convertToParticipantScoreDto)
                .collect(Collectors.toList());
    }

    private ParticipantScoreDto convertToParticipantScoreDto(ChallengeParticipantDto participant) {
        // 이 메소드는 ChallengeParticipantDto 객체를 받아 ParticipantScoreDto 객체를 생성합니다.
        // 예시 구현은 단순화를 위해 일부 필드만 사용하며, 실제 필요한 데이터에 따라 변경될 수 있습니다.
        return ParticipantScoreDto.builder()
                .participantId(participant.getParticipantId())
                .participantUsername(participant.getParticipantUsername())
                // 여기에 추가로 점수, 순위 등의 필드를 계산하거나 설정
                .build();
    }


    public List<ChallengeParticipantDto> getParticipants(Long challengeId) {
        return challengeParticipants.getOrDefault(challengeId, new ArrayList<>());
    }

    public void manageParticipants(Long challengeId, ChallengeParticipantDto participantData) {
        // TODO: 참여자 관리 로직
    }
}

