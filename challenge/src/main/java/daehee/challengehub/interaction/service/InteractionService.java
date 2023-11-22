package daehee.challengehub.interaction.service;

import daehee.challengehub.interaction.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.interaction.model.ChallengeCommentDto;
import daehee.challengehub.interaction.model.ChallengeParticipantDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    public Map<String, Object> postComment(Long challengeId, String commentText) {
        ChallengeCommentDto newComment = ChallengeCommentDto.builder()
                .commentId(1L) // 임시 ID, 실제로는 repository에서 생성
                .challengeId(challengeId)
                .userId(123L) // 예시 사용자 ID
                .commentText(commentText)
                .postedAt("2023-11-15T12:00:00Z")
                .build();

        interactionRepository.postComment(challengeId, newComment);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글 작성 성공");
        response.put("comment", newComment);
        return response;
    }

    public Map<String, Object> getComments(Long challengeId) {
        List<ChallengeCommentDto> comments = interactionRepository.getComments(challengeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글 목록 조회 성공");
        response.put("comments", comments);
        return response;
    }

    public Map<String, Object> getLeaderboard(Long challengeId) {
        List<ChallengeParticipantDto> leaderboard = interactionRepository.getLeaderboard(challengeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "리더보드 조회 성공");
        response.put("leaderboard", leaderboard);
        return response;
    }

    public Map<String, Object> getParticipantDetails(Long challengeId) {
        List<ChallengeParticipantDto> participants = interactionRepository.getParticipants(challengeId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "참여자 상세 정보 조회 성공");
        response.put("participants", participants);
        return response;
    }

    public Map<String, String> manageParticipants(Long challengeId, ChallengeParticipantDto participantData) {
        interactionRepository.manageParticipants(challengeId, participantData);

        Map<String, String> response = new HashMap<>();
        response.put("message", String.format("챌린지 ID %d에 대한 참여자 관리 성공", challengeId));
        return response;
    }
}

