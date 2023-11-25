package daehee.challengehub.social.network.controller;

import daehee.challengehub.social.network.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/network")
public class NetworkController {
    private final NetworkService networkService;

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }


    // TODO: URL 수정한 거 정리해서 반영하기

    @PostMapping("/follow/{followerId}/{followingId}")
    public Map<String, Object> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        return networkService.followUser(followerId, followingId);
    }

    @GetMapping("/following/{userId}")
    public Map<String, Object> getFollowings(@PathVariable Long userId) {
        return networkService.getFollowings(userId);
    }

    @DeleteMapping("/unfollow/{followerId}/{followingId}")
    public Map<String, String> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        return networkService.unfollowUser(followerId, followingId);
    }

    @GetMapping("/followers/{userId}")
    public Map<String, Object> getFollowers(@PathVariable Long userId) {
        return networkService.getFollowers(userId);
    }
}
