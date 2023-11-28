package social.service;

import daehee.challengehub.social.community.model.*;
import daehee.challengehub.social.community.repository.CommunityRepository;
import daehee.challengehub.social.community.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        CommunityFeedResponseDto response = communityService.getCommunityFeed();

        // Then
        assertEquals(mockPosts, response.getPosts());
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
        CreatePostResponseDto response = communityService.createCommunityPost(newPost);

        // Then
        assertEquals("커뮤니티 포스트 생성 성공", response.getMessage());
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
        UpdatePostResponseDto response = communityService.updatePost(postId, updatedPost);

        // Then
        assertEquals("커뮤니티 포스트 수정 성공", response.getMessage());
        verify(communityRepository).updatePost(eq(postId), any(CommunityPostDto.class));
    }

    @Test
    public void deletePost_DeletesPost() {
        // Given
        Long postId = 1L;
        doNothing().when(communityRepository).deletePost(postId);

        // When
        DeletePostResponseDto response = communityService.deletePost(postId);

        // Then
        assertEquals("포스트 삭제 성공: 포스트 ID " + postId, response.getMessage());
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
        CommunityFeedResponseDto response = communityService.getCommunityPosts();

        // Then
        assertEquals(mockPosts, response.getPosts());
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
        LikePostResponseDto response = communityService.likeCommunityPost(postId);

        // Then
        assertEquals(String.format("포스트 ID %d에 좋아요 성공", postId), response.getMessage());
        assertEquals(likedPost.getLikeCount(), response.getNewLikeCount());
        verify(communityRepository).likePost(postId);
    }
}
