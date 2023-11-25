package social.service;

import daehee.challengehub.social.network.repository.NetworkRepository;
import daehee.challengehub.social.network.service.NetworkService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NetworkServiceTest {

    @Mock
    private NetworkRepository networkRepository;

    @InjectMocks
    private NetworkService networkService;

    @Test
    public void followUser_SetsFollowRelationship() {
        // Given
        Long followerId = 1L;
        Long followingId = 2L;
        doNothing().when(networkRepository).followUser(followerId, followingId);

        // When
        Map<String, Object> response = networkService.followUser(followerId, followingId);

        // Then
        assertNotNull(response);
        verify(networkRepository).followUser(followerId, followingId);
    }

    @Test
    public void getFollowings_ReturnsFollowingList() {
        // Given
        Long userId = 1L;
        Set<Long> followingIds = new HashSet<>(Arrays.asList(2L, 3L));
        when(networkRepository.getFollowings(userId)).thenReturn(followingIds);

        // When
        Map<String, Object> response = networkService.getFollowings(userId);

        // Then
        assertNotNull(response);
        verify(networkRepository).getFollowings(userId);
    }

    @Test
    public void unfollowUser_RemovesFollowRelationship() {
        // Given
        Long followerId = 1L;
        Long followingId = 2L;
        doNothing().when(networkRepository).unfollowUser(followerId, followingId);

        // When
        Map<String, String> response = networkService.unfollowUser(followerId, followingId);

        // Then
        assertNotNull(response);
        verify(networkRepository).unfollowUser(followerId, followingId);
    }

    @Test
    public void getFollowers_ReturnsFollowerList() {
        // Given
        Long userId = 1L;
        Set<Long> followerIds = new HashSet<>(Arrays.asList(2L, 3L));
        when(networkRepository.getFollowers(userId)).thenReturn(followerIds);

        // When
        Map<String, Object> response = networkService.getFollowers(userId);

        // Then
        assertNotNull(response);
        verify(networkRepository).getFollowers(userId);
    }
}
