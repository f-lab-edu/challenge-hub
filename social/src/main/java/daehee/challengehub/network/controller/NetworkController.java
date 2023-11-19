package daehee.challengehub.network.controller;

import daehee.challengehub.network.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/network")
public class NetworkController {

    private final NetworkService networkService;

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @PostMapping("/follow/{userId}")
    public Map<String, Object> followUser(@PathVariable Long userId) {
        return networkService.followUser(userId);
    }

    @GetMapping("/following")
    public Map<String, Object> getFollowings() {
        return networkService.getFollowings();
    }

    @DeleteMapping("/follow/{userId}")
    public Map<String, String> unfollowUser(@PathVariable Long userId) {
        return networkService.unfollowUser(userId);
    }

    @GetMapping("/followers")
    public Map<String, Object> getFollowers() {
        return networkService.getFollowers();
    }
}
