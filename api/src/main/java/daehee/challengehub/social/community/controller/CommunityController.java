package daehee.challengehub.social.community.controller;

import daehee.challengehub.social.community.model.CommunityPostDto;
import daehee.challengehub.social.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/feed")
    public Map<String, Object> getCommunityFeed() {
        return communityService.getCommunityFeed();
    }

    @PostMapping("/posts")
    public Map<String, Object> createCommunityPost(@RequestBody CommunityPostDto communityPostDto) {
        return communityService.createCommunityPost(communityPostDto);
    }

    @PutMapping("/posts/{postId}")
    public Map<String, Object> updatePost(@PathVariable Long postId, @RequestBody CommunityPostDto postUpdateData) {
        return communityService.updatePost(postId, postUpdateData);
    }

    @DeleteMapping("/posts/{postId}")
    public Map<String, String> deletePost(@PathVariable Long postId) {
        return communityService.deletePost(postId);
    }

    @GetMapping("/posts")
    public Map<String, Object> getCommunityPosts() {
        return communityService.getCommunityPosts();
    }

    @PostMapping("/posts/{postId}/like")
    public Map<String, Object> likeCommunityPost(@PathVariable Long postId) {
        return communityService.likeCommunityPost(postId);
    }
}
