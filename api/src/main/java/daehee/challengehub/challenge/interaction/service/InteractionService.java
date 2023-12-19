package daehee.challengehub.challenge.interaction.service;

import daehee.challengehub.challenge.interaction.model.ChallengeCommentDto;
import daehee.challengehub.challenge.interaction.model.ChallengeParticipantDto;
import daehee.challengehub.challenge.interaction.model.CommentsResponseDto;
import daehee.challengehub.challenge.interaction.model.LeaderboardResponseDto;
import daehee.challengehub.challenge.interaction.model.ManageParticipantsResponseDto;
import daehee.challengehub.challenge.interaction.model.ParticipantDetailsResponseDto;
import daehee.challengehub.challenge.interaction.model.ParticipantScoreDto;
import daehee.challengehub.challenge.interaction.model.PostCommentResponseDto;
import daehee.challengehub.challenge.interaction.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    // 챌린지에 대한 댓글 작성 로직
    public PostCommentResponseDto postComment(Long id, String commentText) {
        ChallengeCommentDto newComment = ChallengeCommentDto.builder()
                .challengeId(id)
                .userId(123L) // 예시: 현재 로그인한 사용자 ID
                .commentText(commentText)
                .postedAt(Instant.now())
                .build();

        interactionRepository.postComment(id, newComment);
        return new PostCommentResponseDto("댓글 작성 성공", newComment);
    }

    // 챌린지 댓글 목록 조회 로직
    public CommentsResponseDto getComments(Long id) {
        List<ChallengeCommentDto> comments = interactionRepository.getComments(id);
        return new CommentsResponseDto(comments);
    }

    // 챌린지별 리더보드 조회 로직
    public LeaderboardResponseDto getLeaderboard(Long id) {
        // interactionRepository.getLeaderboard(id)가 List<ParticipantScoreDto>를 반환하도록 가정
        List<ParticipantScoreDto> leaderboard = interactionRepository.getLeaderboard(id);

        return new LeaderboardResponseDto(leaderboard);
    }

    // 참여자 상세 정보 조회 로직
    public ParticipantDetailsResponseDto getParticipantDetails(Long id) {
        List<ChallengeParticipantDto> participants = interactionRepository.getParticipants(id);
        return new ParticipantDetailsResponseDto(participants);
    }

    // 참여자 관리 로직
    public ManageParticipantsResponseDto manageParticipants(Long id, ChallengeParticipantDto participantData) {
        // TODO: 참여자 관리 로직 구현
        return new ManageParticipantsResponseDto(String.format("챌린지 ID %d에 대한 참여자 관리 성공", id));
    }
}
