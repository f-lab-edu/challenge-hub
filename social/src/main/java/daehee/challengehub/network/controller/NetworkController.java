package daehee.challengehub.network.controller;

import daehee.challengehub.network.model.FollowDto;
import daehee.challengehub.network.model.FollowersDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/network")
public class NetworkController {
    // 사용자 팔로우
    @PostMapping("/follow/{userId}")
    public Map<String, Object> followUser(@PathVariable Long userId) {
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
        response.put("message", String.format("사용자 %d 팔로우 성공. 상호 팔로우 상태: %s", userId, newFollow.isMutual() ? "예" : "아니오"));
        response.put("followDetails", newFollow);
        return response;
    }

    // 내가 팔로우 하는 사용자 목록 조회
    @GetMapping("/following")
    public Map<String, Object> getFollowings() {
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

    // 사용자 언팔로우
    @DeleteMapping("/follow/{userId}")
    public Map<String, String> unfollowUser(@PathVariable Long userId) {
        userId = 1L;
        Map<String, String> response = new HashMap<>();
        response.put("message", "사용자 ID " + userId + " 언팔로우 성공");
        return response;
    }

    // 나를 팔로우하는 사용자 목록 조회
    @GetMapping("/followers")
    public Map<String, Object> getFollowers() {
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
