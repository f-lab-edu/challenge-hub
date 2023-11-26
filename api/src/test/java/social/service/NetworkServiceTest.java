package social.service;

import daehee.challengehub.social.network.model.FollowResponseDto;
import daehee.challengehub.social.network.model.FollowersResponseDto;
import daehee.challengehub.social.network.model.FollowingResponseDto;
import daehee.challengehub.social.network.model.UnfollowResponseDto;
import daehee.challengehub.social.network.repository.NetworkRepository;
import daehee.challengehub.social.network.service.NetworkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
        FollowResponseDto response = networkService.followUser(followerId, followingId);

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
        FollowingResponseDto response = networkService.getFollowings(userId);

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
        UnfollowResponseDto response = networkService.unfollowUser(followerId, followingId);

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
        FollowersResponseDto response = networkService.getFollowers(userId);

        // Then
        assertNotNull(response);
        verify(networkRepository).getFollowers(userId);
    }
}
