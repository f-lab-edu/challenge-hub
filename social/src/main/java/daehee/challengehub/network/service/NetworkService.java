package daehee.challengehub.network.service;

import daehee.challengehub.network.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import daehee.challengehub.network.model.FollowDto;
import daehee.challengehub.network.model.FollowersDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class NetworkService {
    private final NetworkRepository networkRepository;

    @Autowired
    public NetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public Map<String, Object> followUser(Long followerId, Long followingId) {
        networkRepository.followUser(followerId, followingId);
        boolean isMutual = networkRepository.getFollowers(followingId).contains(followerId);

        // TODO: FollowDto 생성 및 설정
        FollowDto newFollow = FollowDto.builder()
                .followerId(123L) // 임의의 팔로워 ID
                .followingId(456L) // 팔로우 대상 ID
                .followDate("2023-11-15")
                .isMutual(true)
                .followerUsername("follower_username")
                .followingUsername("following_username")
                .followerProfileImage("https://example.com/follower_image.jpg")
                .followingProfileImage("https://example.com/following_image.jpg")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", String.format("사용자 %d 팔로우 성공. 상호 팔로우 상태: %s", followingId, isMutual ? "예" : "아니오"));
        response.put("followDetails", newFollow);
        return response;
    }

    public Map<String, Object> getFollowings(Long userId) {
        Set<Long> followingIds = networkRepository.getFollowings(userId);
        // TODO: FollowDto 리스트 생성
        List<FollowDto> followingList = Arrays.asList(
                FollowDto.builder()
                        .followerId(123L)
                        .followingId(456L)
                        .followDate("2023-01-01")
                        .isMutual(true)
                        .followerUsername("follower1_username")
                        .followingUsername("user_username")
                        .followerProfileImage("https://example.com/follower1_image.jpg")
                        .followingProfileImage("https://example.com/user_image.jpg")
                        .build(),
                FollowDto.builder()
                        .followerId(789L)
                        .followingId(456L)
                        .followDate("2023-02-01")
                        .isMutual(false)
                        .followerUsername("follower2_username")
                        .followingUsername("user_username")
                        .followerProfileImage("https://example.com/follower2_image.jpg")
                        .followingProfileImage("https://example.com/user_image.jpg")
                        .build()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "팔로잉 목록 조회 성공");
        response.put("followingList", followingList);
        return response;
    }

    public Map<String, String> unfollowUser(Long followerId, Long followingId) {
        networkRepository.unfollowUser(followerId, followingId);

        Map<String, String> response = new HashMap<>();
        response.put("message", String.format("사용자 ID %d 언팔로우 성공", followingId));
        return response;
    }

    public Map<String, Object> getFollowers(Long userId) {
        Set<Long> followerIds = networkRepository.getFollowers(userId);
        // TODO: FollowersDto 리스트 생성
        List<FollowersDto> followers = Arrays.asList(
                FollowersDto.builder()
                        .userId(789L)
                        .username("follower_user1")
                        .profileImage("https://example.com/follower1.jpg")
                        .isFollowedBack(true)
                        .build(),
                FollowersDto.builder()
                        .userId(101L)
                        .username("follower_user2")
                        .profileImage("https://example.com/follower2.jpg")
                        .isFollowedBack(false)
                        .build()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "팔로워 목록 조회 성공");
        response.put("followers", followers);
        return response;
    }
}

