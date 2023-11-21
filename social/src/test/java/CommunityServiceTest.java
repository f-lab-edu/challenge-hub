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
                // Mock 커뮤니티 포스트 데이터 생성
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
        CommunityPostDto newPost = new CommunityPostDto();
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
        CommunityPostDto updatedPost = new CommunityPostDto();
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
                // Mock 커뮤니티 포스트 데이터 생성
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
        CommunityPostDto likedPost = new CommunityPostDto();
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
