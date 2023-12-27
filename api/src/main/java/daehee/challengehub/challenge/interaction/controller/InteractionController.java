package daehee.challengehub.challenge.interaction.controller;

import daehee.challengehub.challenge.interaction.model.ChallengeParticipantDto;
import daehee.challengehub.challenge.interaction.model.CommentsResponseDto;
import daehee.challengehub.challenge.interaction.model.LeaderboardResponseDto;
import daehee.challengehub.challenge.interaction.model.ManageParticipantsResponseDto;
import daehee.challengehub.challenge.interaction.model.ParticipantDetailsResponseDto;
import daehee.challengehub.challenge.interaction.model.PostCommentResponseDto;
import daehee.challengehub.challenge.interaction.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/challenges")
public class InteractionController {
    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    // 챌린지에 대한 댓글 작성 API
    // {id}는 챌린지의 고유 ID를 나타냄
    @PostMapping("/{id}/comments")
    public PostCommentResponseDto postComment(@PathVariable Long id, @RequestBody String commentText) {
        return interactionService.postComment(id, commentText);
    }

    // 특정 챌린지의 모든 댓글을 조회하는 API
    @GetMapping("/{id}/comments")
    public CommentsResponseDto getComments(@PathVariable Long id) {
        return interactionService.getComments(id);
    }

    // 챌린지별 리더보드 조회 API
    @GetMapping("/{id}/leaderboard")
    public LeaderboardResponseDto getLeaderboard(@PathVariable Long id) {
        return interactionService.getLeaderboard(id);
    }

    // 챌린지 참여자들의 상세 정보 조회 API
    @GetMapping("/{id}/participants/details")
    public ParticipantDetailsResponseDto getParticipantDetails(@PathVariable Long id) {
        return interactionService.getParticipantDetails(id);
    }

    // 챌린지 참여자들의 상태를 관리하는 API
    @PostMapping("/{id}/participants/manage")
    public ManageParticipantsResponseDto manageParticipants(@PathVariable Long id, @RequestBody ChallengeParticipantDto participantData) {
        return interactionService.manageParticipants(id, participantData);
    }
}
