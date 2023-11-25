package daehee.challengehub.challenge.interaction.service;

import daehee.challengehub.challenge.interaction.model.ChallengeCommentDto;
import daehee.challengehub.challenge.interaction.model.ChallengeParticipantDto;
import daehee.challengehub.challenge.interaction.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    // 챌린지에 대한 댓글 작성 로직
    public Map<String, Object> postComment(Long id, String commentText) {
        ChallengeCommentDto newComment = ChallengeCommentDto.builder()
                .challengeId(id)
                .userId(123L) // 예시: 현재 로그인한 사용자 ID
                .commentText(commentText)
                .postedAt(LocalDateTime.now().toString())
                .build();

        interactionRepository.postComment(id, newComment);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글 작성 성공");
        response.put("comment", newComment);
        return response;
    }

    // 챌린지 댓글 목록 조회 로직
    public Map<String, Object> getComments(Long id) {
        List<ChallengeCommentDto> comments = interactionRepository.getComments(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글 목록 조회 성공");
        response.put("comments", comments);
        return response;
    }

    // 챌린지별 리더보드 조회 로직
    public Map<String, Object> getLeaderboard(Long id) {
        List<ChallengeParticipantDto> leaderboard = interactionRepository.getLeaderboard(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "리더보드 조회 성공");
        response.put("leaderboard", leaderboard);
        return response;
    }

    // 참여자 상세 정보 조회 로직
    public Map<String, Object> getParticipantDetails(Long id) {
        List<ChallengeParticipantDto> participants = interactionRepository.getParticipants(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "참여자 상세 정보 조회 성공");
        response.put("participants", participants);
        return response;
    }

    // 참여자 관리 로직 (인증이 안된 경우 추방하는 기능은 미구현 상태)
    public Map<String, String> manageParticipants(Long id, ChallengeParticipantDto participantData) {
        // TODO: 참여자 관리 로직 구현
        // 현재는 단순한 메시지 반환으로 처리
        Map<String, String> response = new HashMap<>();
        response.put("message", String.format("챌린지 ID %d에 대한 참여자 관리 성공", id));
        return response;
    }
}

