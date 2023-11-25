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
import java.util.stream.Collectors;

@Service
public class NetworkService {
    private final NetworkRepository networkRepository;
    private final Long loggedInUserId = 123L; // 현재 로그인한 사용자 ID (임시로 설정)

    @Autowired
    public NetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public Map<String, Object> followUser(Long followerId, Long followingId) {
        networkRepository.followUser(followerId, followingId);
        boolean isMutual = networkRepository.getFollowers(followingId).contains(followerId);

        FollowDto newFollow = FollowDto.builder()
                .followerId(followerId)
                .followingId(followingId)
                .followDate("2023-11-15")
                .isMutual(isMutual)
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
        // 실제 팔로잉 ID 목록에 기반하여 FollowDto 리스트 생성
        List<FollowDto> followingList = followingIds.stream()
                .map(followingId -> createFollowDto(userId, followingId)) // 이 메서드는 실제 FollowDto 생성 로직을 포함해야 함
                .collect(Collectors.toList());

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
        // 실제 팔로워 ID 목록에 기반하여 FollowersDto 리스트 생성
        List<FollowersDto> followers = followerIds.stream()
                .map(this::createFollowersDto) // 이 메서드는 실제 FollowersDto 생성 로직을 포함해야 함
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "팔로워 목록 조회 성공");
        response.put("followers", followers);
        return response;
    }

    private FollowDto createFollowDto(Long followerId, Long followingId) {
        // 실제는 데이터베이스나 서비스를 통해 정보를 가져와야 함
        String followerUsername = "follower_" + followerId;
        String followingUsername = "following_" + followingId;
        String followerProfileImage = "https://example.com/follower_" + followerId + "_image.jpg";
        String followingProfileImage = "https://example.com/following_" + followingId + "_image.jpg";

        return FollowDto.builder()
                .followerId(followerId)
                .followingId(followingId)
                .followDate("2023-11-15")
                .isMutual(networkRepository.getFollowers(followingId).contains(followerId))
                .followerUsername(followerUsername)
                .followingUsername(followingUsername)
                .followerProfileImage(followerProfileImage)
                .followingProfileImage(followingProfileImage)
                .build();
    }

    private FollowersDto createFollowersDto(Long followerId) {
        // 실제는 데이터베이스나 서비스를 통해 정보를 가져와야 함
        String followerUsername = "follower_" + followerId;
        String followerProfileImage = "https://example.com/follower_" + followerId + "_image.jpg";

        return FollowersDto.builder()
                .userId(followerId)
                .username(followerUsername)
                .profileImage(followerProfileImage)
                .isFollowedBack(networkRepository.getFollowings(followerId).contains(loggedInUserId)) // loggedInUserId는 현재 로그인한 사용자의 ID
                .build();
    }

}

