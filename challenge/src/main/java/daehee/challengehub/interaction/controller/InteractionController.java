package daehee.challengehub.interaction.controller;

import daehee.challengehub.interaction.model.ChallengeParticipantDto;
import daehee.challengehub.interaction.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("/challenges")
public class InteractionController {
    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping("/{id}/comments")
    public Map<String, Object> postComment(@PathVariable Long id, @RequestBody String commentText) {
        return interactionService.postComment(id, commentText);
    }

    @GetMapping("/{id}/comments")
    public Map<String, Object> getComments(@PathVariable Long id) {
        return interactionService.getComments(id);
    }

    @GetMapping("/{id}/leaderboard")
    public Map<String, Object> getLeaderboard(@PathVariable Long id) {
        return interactionService.getLeaderboard(id);
    }

    @GetMapping("/{id}/participants/details")
    public Map<String, Object> getParticipantDetails(@PathVariable Long id) {
        return interactionService.getParticipantDetails(id);
    }

    @PostMapping("/{id}/participants/manage")
    public Map<String, String> manageParticipants(@PathVariable Long id, @RequestBody ChallengeParticipantDto participantData) {
        return interactionService.manageParticipants(id, participantData);
    }
}
