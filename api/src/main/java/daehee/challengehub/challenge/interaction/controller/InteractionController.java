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

    @PostMapping("/{id}/comments")
    public PostCommentResponseDto postComment(@PathVariable Long id, @RequestBody String commentText) {
        return interactionService.postComment(id, commentText);
    }

    @GetMapping("/{id}/comments")
    public CommentsResponseDto getComments(@PathVariable Long id) {
        return interactionService.getComments(id);
    }

    @GetMapping("/{id}/leaderboard")
    public LeaderboardResponseDto getLeaderboard(@PathVariable Long id) {
        return interactionService.getLeaderboard(id);
    }

    @GetMapping("/{id}/participants/details")
    public ParticipantDetailsResponseDto getParticipantDetails(@PathVariable Long id) {
        return interactionService.getParticipantDetails(id);
    }

    @PostMapping("/{id}/participants/manage")
    public ManageParticipantsResponseDto manageParticipants(@PathVariable Long id, @RequestBody ChallengeParticipantDto participantData) {
        return interactionService.manageParticipants(id, participantData);
    }
}
