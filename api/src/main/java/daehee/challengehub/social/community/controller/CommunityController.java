package daehee.challengehub.social.community.controller;

import daehee.challengehub.social.community.model.*;
import daehee.challengehub.social.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/feed")
    public CommunityFeedResponseDto getCommunityFeed() {
        return communityService.getCommunityFeed();
    }

    @PostMapping("/posts")
    public CreatePostResponseDto createCommunityPost(@RequestBody CommunityPostDto communityPostDto) {
        return communityService.createCommunityPost(communityPostDto);
    }

    @PutMapping("/posts/{postId}")
    public UpdatePostResponseDto updatePost(@PathVariable Long postId, @RequestBody CommunityPostDto postUpdateData) {
        return communityService.updatePost(postId, postUpdateData);
    }

    @DeleteMapping("/posts/{postId}")
    public DeletePostResponseDto deletePost(@PathVariable Long postId) {
        return communityService.deletePost(postId);
    }

    @GetMapping("/posts")
    public CommunityFeedResponseDto getCommunityPosts() {
        return communityService.getCommunityPosts();
    }

    @PostMapping("/posts/{postId}/like")
    public LikePostResponseDto likeCommunityPost(@PathVariable Long postId) {
        return communityService.likeCommunityPost(postId);
    }
}
