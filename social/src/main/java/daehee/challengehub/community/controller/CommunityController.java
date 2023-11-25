package daehee.challengehub.community.controller;

import daehee.challengehub.community.model.CommunityPostDto;
import daehee.challengehub.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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
