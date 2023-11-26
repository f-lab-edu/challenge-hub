package daehee.challengehub.social.network.service;

import daehee.challengehub.social.network.model.*;
import daehee.challengehub.social.network.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public FollowResponseDto followUser(Long followerId, Long followingId) {
        networkRepository.followUser(followerId, followingId);
        boolean isMutual = networkRepository.getFollowers(followingId).contains(followerId);
        FollowDto newFollow = createFollowDto(followerId, followingId);

        return new FollowResponseDto(
                String.format("사용자 %d 팔로우 성공. 상호 팔로우 상태: %s", followingId, isMutual ? "예" : "아니오"),
                newFollow
        );
    }

    public FollowingResponseDto getFollowings(Long userId) {
        Set<Long> followingIds = networkRepository.getFollowings(userId);
        List<FollowDto> followingList = followingIds.stream()
                .map(followingId -> createFollowDto(userId, followingId))
                .collect(Collectors.toList());

        return new FollowingResponseDto(followingList);
    }

    public UnfollowResponseDto unfollowUser(Long followerId, Long followingId) {
        networkRepository.unfollowUser(followerId, followingId);
        return new UnfollowResponseDto(String.format("사용자 ID %d 언팔로우 성공", followingId));
    }

    public FollowersResponseDto getFollowers(Long userId) {
        Set<Long> followerIds = networkRepository.getFollowers(userId);
        List<FollowersDto> followers = followerIds.stream()
                .map(this::createFollowersDto)
                .collect(Collectors.toList());

        return new FollowersResponseDto(followers);
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

