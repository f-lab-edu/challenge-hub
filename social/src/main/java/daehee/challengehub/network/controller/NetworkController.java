package daehee.challengehub.network.controller;

import daehee.challengehub.network.model.FollowDto;
import daehee.challengehub.network.model.FollowRequestDto;
import daehee.challengehub.network.model.FollowersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/network")
public class NetworkController {
    // 사용자 팔로우
    @PostMapping("/follow/{userId}")
    public String followUser(@PathVariable Long userId) {
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

        String responseMessage = String.format("사용자 %d 팔로우 성공. 상호 팔로우 상태: %s",
                newFollow.getFollowingId(), newFollow.isMutual() ? "예" : "아니오");
        return responseMessage;
    }

    // 내가 팔로우하는 사용자 목록 조회
    @GetMapping("/following")
    public List<FollowDto> getFollowersList() {
        List<FollowDto> followersList = Arrays.asList(
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

        return followersList;
    }

    // 사용자 언팔로우
    @DeleteMapping("/follow/{userId}")
    public String unfollowUser(@PathVariable Long userId) {
        userId = 1L;
        return "사용자 ID " + userId + " 언팔로우 성공";
    }

    // 나를 팔로우하는 사용자 목록 조회
    @GetMapping("/followers")
    public List<FollowersDto> getFollowers() {
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

        return followers;
    }

    // 팔로우 요청 목록 조회
    @GetMapping("/follow/requests")
    public List<FollowRequestDto> getFollowRequests() {
        List<FollowRequestDto> requests = Arrays.asList(
                FollowRequestDto.builder()
                        .requestId(1L)
                        .requesterId(111L)
                        .requestedId(222L)
                        .requestDate("2023-05-02")
                        .requesterUsername("requester1")
                        .requesterProfileImage("https://example.com/requester1.jpg")
                        .build(),
                FollowRequestDto.builder()
                        .requestId(2L)
                        .requesterId(333L)
                        .requestedId(444L)
                        .requestDate("2023-05-03")
                        .requesterUsername("requester2")
                        .requesterProfileImage("https://example.com/requester2.jpg")
                        .build()
        );

        return requests;
    }

    // 팔로우 요청 응답
    @PostMapping("/follow/respond")
    public String respondToFollowRequest(@RequestBody FollowRequestDto requestDto) {
        Long requestId = 3L;
        return "팔로우 요청 ID " + requestId + "에 대한 응답 처리 완료";
    }
}
