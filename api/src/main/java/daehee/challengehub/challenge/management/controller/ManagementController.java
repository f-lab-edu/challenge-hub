package daehee.challengehub.challenge.management.controller;

import daehee.challengehub.challenge.management.model.*;
import daehee.challengehub.challenge.management.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenges")
public class ManagementController {
    private final ManagementService managementService;

    @Autowired
    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public CreateChallengeResponseDto createChallenge(@RequestBody ChallengeDto challengeData) {
        return managementService.createChallenge(challengeData);
    }

    @GetMapping
    public GetAllChallengesResponseDto getAllChallenges() {
        return managementService.getAllChallenges();
    }

    @GetMapping("/{id}")
    public GetChallengeResponseDto getChallengeById(@PathVariable Long id) {
        return managementService.getChallengeById(id);
    }

    @PutMapping("/{id}")
    public UpdateChallengeResponseDto updateChallenge(@PathVariable Long id, @RequestBody ChallengeDto challengeData) {
        return managementService.updateChallenge(id, challengeData);
    }

    @DeleteMapping("/{id}")
    public DeleteChallengeResponseDto deleteChallenge(@PathVariable Long id) {
        return managementService.deleteChallenge(id);
    }

    @PostMapping("/{id}/participation")
    public ParticipateInChallengeResponseDto participateInChallenge(@PathVariable Long id) {
        return managementService.participateInChallenge(id);
    }

    @PostMapping("/{id}/tags")
    public AddTagResponseDto addTagToChallenge(@PathVariable Long id, @RequestBody ChallengeTagDto tagData) {
        return managementService.addTagToChallenge(id, tagData);
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public RemoveTagResponseDto removeTagFromChallenge(@PathVariable Long id, @PathVariable Long tagId) {
        return managementService.removeTagFromChallenge(id, tagId);
    }

    @PostMapping("/{id}/images")
    public UploadImageResponseDto uploadImageToChallenge(@PathVariable Long id, @RequestBody ChallengeImageDto imageData) {
        return managementService.uploadImageToChallenge(id, imageData);
    }

    @DeleteMapping("/{id}/images/{imageId}")
    public RemoveImageResponseDto removeImageFromChallenge(@PathVariable Long id, @PathVariable Long imageId) {
        return managementService.removeImageFromChallenge(id, imageId);
    }
}
