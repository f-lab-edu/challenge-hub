package daehee.challengehub.social.network.controller;

import daehee.challengehub.social.network.model.FollowResponseDto;
import daehee.challengehub.social.network.model.FollowersResponseDto;
import daehee.challengehub.social.network.model.FollowingResponseDto;
import daehee.challengehub.social.network.model.UnfollowResponseDto;
import daehee.challengehub.social.network.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/network")
public class NetworkController {
    private final NetworkService networkService;

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @PostMapping("/follow/{followerId}/{followingId}")
    public FollowResponseDto followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        return networkService.followUser(followerId, followingId);
    }

    @GetMapping("/following/{userId}")
    public FollowingResponseDto getFollowings(@PathVariable Long userId) {
        return networkService.getFollowings(userId);
    }

    @DeleteMapping("/unfollow/{followerId}/{followingId}")
    public UnfollowResponseDto unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        return networkService.unfollowUser(followerId, followingId);
    }

    @GetMapping("/followers/{userId}")
    public FollowersResponseDto getFollowers(@PathVariable Long userId) {
        return networkService.getFollowers(userId);
    }
}
