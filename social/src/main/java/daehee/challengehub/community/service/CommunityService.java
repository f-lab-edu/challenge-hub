package daehee.challengehub.community.service;

import daehee.challengehub.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.community.model.CommunityPostDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    // 커뮤니티 피드 조회 로직
    public Map<String, Object> getCommunityFeed() {
        List<CommunityPostDto> communityFeed = communityRepository.getAllPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "커뮤니티 피드 조회 성공");
        response.put("communityFeed", communityFeed);
        return response;
    }

    // 커뮤니티 포스트 작성 로직
    public Map<String, Object> createCommunityPost(CommunityPostDto communityPostDto) {
        communityRepository.savePost(communityPostDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "커뮤니티 포스트 생성 성공");
        response.put("createdPost", communityPostDto);
        return response;
    }

    // 커뮤니티 포스트 수정 로직
    public Map<String, Object> updatePost(Long postId, CommunityPostDto postUpdateData) {
        communityRepository.updatePost(postId, postUpdateData);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "커뮤니티 포스트 수정 성공");
        response.put("updatedPost", postUpdateData);
        return response;
    }

    // 커뮤니티 포스트 삭제 로직
    public Map<String, String> deletePost(Long postId) {
        communityRepository.deletePost(postId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "포스트 삭제 성공: 포스트 ID " + postId);
        return response;
    }

    // 커뮤니티 포스트 목록 조회 로직
    public Map<String, Object> getCommunityPosts() {
        List<CommunityPostDto> posts = communityRepository.getAllPosts();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "커뮤니티 포스트 목록 조회 성공");
        response.put("posts", posts);
        return response;
    }

    // 포스트 좋아요 로직
    // TODO: 좋아요 관련 로직 구현이 단순히 1만 증가한다고 되는건지 다시 검토하기
    public Map<String, Object> likeCommunityPost(Long postId) {
        communityRepository.likePost(postId);
        CommunityPostDto likedPost = communityRepository.findPostById(postId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", String.format("포스트 ID %d에 좋아요 성공", postId));
        response.put("likedPost", likedPost);
        return response;
    }
}
