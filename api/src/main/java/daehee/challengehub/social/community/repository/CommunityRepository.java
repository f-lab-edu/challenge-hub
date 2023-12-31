package daehee.challengehub.social.community.repository;

import daehee.challengehub.social.community.model.CommunityPostDto;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommunityRepository {
    private final Map<Long, CommunityPostDto> communityPosts = new HashMap<>();
    private long postIdCounter = 1;

    public CommunityRepository() {
        CommunityPostDto post1 = CommunityPostDto.builder()
                .postId(postIdCounter++)
                .authorId(100L)
                .postContent("첫 번째 커뮤니티 포스트 내용")
                .postTitle("첫 번째 커뮤니티 포스트")
                .creationDate(Instant.parse("2023-11-10T13:00:00Z"))
                .lastEdited(Instant.parse("2023-11-10T13:00:00Z"))
                .likeCount(15)
                .commentCount(4)
                .build();
        communityPosts.put(post1.getPostId(), post1);

        CommunityPostDto post2 = CommunityPostDto.builder()
                .postId(postIdCounter++)
                .authorId(101L)
                .postContent("두 번째 커뮤니티 포스트 내용")
                .postTitle("두 번째 커뮤니티 포스트")
                .creationDate(Instant.parse("2023-11-11T13:00:00Z"))
                .lastEdited(Instant.parse("2023-11-11T13:00:00Z"))
                .likeCount(10)
                .commentCount(2)
                .build();
        communityPosts.put(post2.getPostId(), post2);
    }

    public void savePost(CommunityPostDto post) {
        Long newPostId = postIdCounter++;
        CommunityPostDto newPost = CommunityPostDto.builder()
                .postId(newPostId)
                .authorId(post.getAuthorId())
                .postContent(post.getPostContent())
                .postTitle(post.getPostTitle())
                .creationDate(post.getCreationDate())
                .lastEdited(post.getLastEdited())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .build();
        communityPosts.put(newPostId, newPost);
    }

    public CommunityPostDto findPostById(Long postId) {
        return communityPosts.get(postId);
    }

    public void updatePost(Long postId, CommunityPostDto updatedPost) {
        if (communityPosts.containsKey(postId)) {
            communityPosts.put(postId, updatedPost);
        }
    }

    public void deletePost(Long postId) {
        communityPosts.remove(postId);
    }

    public List<CommunityPostDto> getAllPosts() {
        return new ArrayList<>(communityPosts.values());
    }

    public void likePost(Long postId) {
        CommunityPostDto post = findPostById(postId);
        if (post != null) {
            CommunityPostDto updatedPost = CommunityPostDto.builder()
                    .postId(post.getPostId())
                    .authorId(post.getAuthorId())
                    .postContent(post.getPostContent())
                    .postTitle(post.getPostTitle())
                    .creationDate(post.getCreationDate())
                    .lastEdited(post.getLastEdited())
                    .likeCount(post.getLikeCount() + 1) // 좋아요 수 업데이트
                    .commentCount(post.getCommentCount())
                    .build();
            communityPosts.put(postId, updatedPost);
        }
    }

}
