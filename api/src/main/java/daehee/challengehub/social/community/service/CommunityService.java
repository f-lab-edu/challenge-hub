package daehee.challengehub.social.community.service;

import daehee.challengehub.social.community.model.*;
import daehee.challengehub.social.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    // 커뮤니티 피드 조회 로직
    public CommunityFeedResponseDto getCommunityFeed() {
        List<CommunityPostDto> communityFeed = communityRepository.getAllPosts();
        return new CommunityFeedResponseDto(communityFeed);
    }

    // 커뮤니티 포스트 작성 로직
    public CreatePostResponseDto createCommunityPost(CommunityPostDto communityPostDto) {
        communityRepository.savePost(communityPostDto);
        return new CreatePostResponseDto("커뮤니티 포스트 생성 성공", communityPostDto);
    }

    // 커뮤니티 포스트 수정 로직
    public UpdatePostResponseDto updatePost(Long postId, CommunityPostDto postUpdateData) {
        communityRepository.updatePost(postId, postUpdateData);
        return new UpdatePostResponseDto("커뮤니티 포스트 수정 성공", postUpdateData);
    }

    // 커뮤니티 포스트 삭제 로직
    public DeletePostResponseDto deletePost(Long postId) {
        communityRepository.deletePost(postId);
        return new DeletePostResponseDto("포스트 삭제 성공: 포스트 ID " + postId);
    }

    // 커뮤니티 포스트 목록 조회 로직
    public CommunityFeedResponseDto getCommunityPosts() {
        List<CommunityPostDto> posts = communityRepository.getAllPosts();
        return new CommunityFeedResponseDto(posts);
    }

    // 포스트 좋아요 로직
    public LikePostResponseDto likeCommunityPost(Long postId) {
        communityRepository.likePost(postId);
        CommunityPostDto likedPost = communityRepository.findPostById(postId);
        return new LikePostResponseDto(String.format("포스트 ID %d에 좋아요 성공", postId), likedPost.getLikeCount());
    }
}
