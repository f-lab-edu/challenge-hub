import daehee.challengehub.community.model.CommunityPostDto;
import daehee.challengehub.community.repository.CommunityRepository;
import daehee.challengehub.community.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
public class CommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private CommunityService communityService;

    @Test
    public void getCommunityFeed_ReturnsFeed() {
        // Given
        List<CommunityPostDto> mockPosts = Arrays.asList(
                CommunityPostDto.builder()
                        .postId(1L)
                        .authorId(100L)
                        .postContent("첫 번째 커뮤니티 포스트 내용")
                        .postTitle("첫 번째 커뮤니티 포스트")
                        .creationDate("2023-11-10")
                        .lastEdited("2023-11-10")
                        .likeCount(15)
                        .commentCount(4)
                        .build(),
                CommunityPostDto.builder()
                        .postId(2L)
                        .authorId(101L)
                        .postContent("두 번째 커뮤니티 포스트 내용")
                        .postTitle("두 번째 커뮤니티 포스트")
                        .creationDate("2023-11-11")
                        .lastEdited("2023-11-11")
                        .likeCount(10)
                        .commentCount(2)
                        .build()
        );
        when(communityRepository.getAllPosts()).thenReturn(mockPosts);

        // When
        Map<String, Object> response = communityService.getCommunityFeed();

        // Then
        assertEquals("커뮤니티 피드 조회 성공", response.get("message"));
        assertEquals(mockPosts, response.get("communityFeed"));
    }

    @Test
    public void createCommunityPost_CreatesPost() {
        // Given
        CommunityPostDto newPost = CommunityPostDto.builder()
                .postId(1L)
                .authorId(100L)
                .postContent("새 게시물 내용")
                .postTitle("새 게시물 제목")
                .creationDate("2023-11-15")
                .lastEdited("2023-11-15")
                .likeCount(0)
                .commentCount(0)
                .build();
        doNothing().when(communityRepository).savePost(any(CommunityPostDto.class));

        // When
        Map<String, Object> response = communityService.createCommunityPost(newPost);

        // Then
        assertEquals("커뮤니티 포스트 생성 성공", response.get("message"));
        verify(communityRepository).savePost(any(CommunityPostDto.class));
    }


    @Test
    public void updatePost_UpdatesExistingPost() {
        // Given
        Long postId = 1L;
        CommunityPostDto updatedPost = CommunityPostDto.builder()
                .postId(postId)
                .authorId(100L)
                .postContent("수정된 게시물 내용")
                .postTitle("수정된 게시물 제목")
                .creationDate("2023-11-15")
                .lastEdited("2023-11-15")
                .likeCount(10)
                .commentCount(5)
                .build();
        doNothing().when(communityRepository).updatePost(eq(postId), any(CommunityPostDto.class));

        // When
        Map<String, Object> response = communityService.updatePost(postId, updatedPost);

        // Then
        assertEquals("커뮤니티 포스트 수정 성공", response.get("message"));
        verify(communityRepository).updatePost(eq(postId), any(CommunityPostDto.class));
    }

    @Test
    public void deletePost_DeletesPost() {
        // Given
        Long postId = 1L;
        doNothing().when(communityRepository).deletePost(postId);

        // When
        Map<String, String> response = communityService.deletePost(postId);

        // Then
        assertEquals("포스트 삭제 성공: 포스트 ID " + postId, response.get("message"));
        verify(communityRepository).deletePost(postId);
    }

    @Test
    public void getCommunityPosts_ReturnsPosts() {
        // Given
        List<CommunityPostDto> mockPosts = Arrays.asList(
                CommunityPostDto.builder()
                        .postId(1L)
                        .authorId(100L)
                        .postContent("첫 번째 커뮤니티 포스트 내용")
                        .postTitle("첫 번째 커뮤니티 포스트")
                        .creationDate("2023-11-10")
                        .lastEdited("2023-11-10")
                        .likeCount(15)
                        .commentCount(4)
                        .build(),
                CommunityPostDto.builder()
                        .postId(2L)
                        .authorId(101L)
                        .postContent("두 번째 커뮤니티 포스트 내용")
                        .postTitle("두 번째 커뮤니티 포스트")
                        .creationDate("2023-11-11")
                        .lastEdited("2023-11-11")
                        .likeCount(10)
                        .commentCount(2)
                        .build()
        );
        when(communityRepository.getAllPosts()).thenReturn(mockPosts);

        // When
        Map<String, Object> response = communityService.getCommunityPosts();

        // Then
        assertEquals("커뮤니티 포스트 목록 조회 성공", response.get("message"));
        assertEquals(mockPosts, response.get("posts"));
    }

    @Test
    public void likeCommunityPost_AddsLikeToPost() {
        // Given
        Long postId = 1L;
        CommunityPostDto likedPost = CommunityPostDto.builder()
                .postId(postId)
                .authorId(100L)
                .postContent("게시물 내용")
                .postTitle("게시물 제목")
                .creationDate("2023-11-15")
                .lastEdited("2023-11-15")
                .likeCount(5)
                .commentCount(2)
                .build();
        when(communityRepository.findPostById(postId)).thenReturn(likedPost);
        doNothing().when(communityRepository).likePost(postId);


        // When
        Map<String, Object> response = communityService.likeCommunityPost(postId);

        // Then
        assertEquals(String.format("포스트 ID %d에 좋아요 성공", postId), response.get("message"));
        assertEquals(likedPost, response.get("likedPost"));
        verify(communityRepository).likePost(postId);
    }
}
