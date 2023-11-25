package daehee.challengehub.management.controller;

import daehee.challengehub.management.model.ChallengeDto;
import daehee.challengehub.management.model.ChallengeImageDto;
import daehee.challengehub.management.model.ChallengeTagDto;
import daehee.challengehub.management.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("/challenges")
public class ManagementController {
    private final ManagementService managementService;

    @Autowired
    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public Map<String, Object> createChallenge(@RequestBody ChallengeDto challengeData) {
        return managementService.createChallenge(challengeData);
    }

    @GetMapping
    public Map<String, Object> getAllChallenges() {
        return managementService.getAllChallenges();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getChallengeById(@PathVariable Long id) {
        return managementService.getChallengeById(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateChallenge(@PathVariable Long id, @RequestBody ChallengeDto challengeData) {
        return managementService.updateChallenge(id, challengeData);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteChallenge(@PathVariable Long id) {
        return managementService.deleteChallenge(id);
    }

    @PostMapping("/{id}/participation")
    public Map<String, String> participateInChallenge(@PathVariable Long id) {
        return managementService.participateInChallenge(id);
    }

    @PostMapping("/{id}/tags")
    public Map<String, Object> addTagToChallenge(@PathVariable Long id, @RequestBody ChallengeTagDto tagData) {
        return managementService.addTagToChallenge(id, tagData);
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public Map<String, String> removeTagFromChallenge(@PathVariable Long id, @PathVariable Long tagId) {
        return managementService.removeTagFromChallenge(id, tagId);
    }

    @PostMapping("/{id}/images")
    public Map<String, Object> uploadImageToChallenge(@PathVariable Long id, @RequestBody ChallengeImageDto imageData) {
        return managementService.uploadImageToChallenge(id, imageData);
    }

    @DeleteMapping("/{id}/images/{imageId}")
    public Map<String, String> removeImageFromChallenge(@PathVariable Long id, @PathVariable Long imageId) {
        return managementService.removeImageFromChallenge(id, imageId);
    }
}
