package daehee.challengehub.social.community.controller;


import daehee.challengehub.social.community.model.CommunityFeedResponseDto;
import daehee.challengehub.social.community.model.CommunityPostDto;
import daehee.challengehub.social.community.model.CreatePostResponseDto;
import daehee.challengehub.social.community.model.DeletePostResponseDto;
import daehee.challengehub.social.community.model.LikePostResponseDto;
import daehee.challengehub.social.community.model.UpdatePostResponseDto;
import daehee.challengehub.social.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
