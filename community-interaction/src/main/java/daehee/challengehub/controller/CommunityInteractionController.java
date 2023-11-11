package daehee.challengehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import daehee.challengehub.service.CommunityInteractionService;

@RestController
public class CommunityInteractionController {

    private final CommunityInteractionService communityService;

    @Autowired
    public CommunityInteractionController(CommunityInteractionService communityService) {
        this.communityService = communityService;
    }

    @PostMapping("/friends")
    public ResponseEntity<String> addFriend(@RequestBody String friendId) {
        return ResponseEntity.ok(communityService.addFriend(friendId));
    }

    @GetMapping("/friends")
    public ResponseEntity<String> getFriendsList() {
        return ResponseEntity.ok(communityService.getFriendsList());
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(@PathVariable String userId) {
        return ResponseEntity.ok(communityService.followUser(userId));
    }

    @GetMapping("/followers")
    public ResponseEntity<String> getFollowersList() {
        return ResponseEntity.ok(communityService.getFollowersList());
    }

    @PostMapping("/groups")
    public ResponseEntity<String> createGroup(@RequestBody String groupName) {
        return ResponseEntity.ok(communityService.createGroup(groupName));
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<String> getGroupDetails(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getGroupDetails(id));
    }

    @PostMapping("/groups/{id}/messages")
    public ResponseEntity<String> sendMessageToGroup(@PathVariable Long id, @RequestBody String message) {
        return ResponseEntity.ok(communityService.sendMessageToGroup(id, message));
    }

    @GetMapping("/groups/{id}/messages")
    public ResponseEntity<String> getGroupMessages(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getGroupMessages(id));
    }

    @GetMapping("/user/{userId}/profile")
    public ResponseEntity<String> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(communityService.getUserProfile(userId));
    }

    @PostMapping("/user/{userId}/message")
    public ResponseEntity<String> sendMessageToUser(@PathVariable Long userId, @RequestBody String message) {
        return ResponseEntity.ok(communityService.sendMessageToUser(userId, message));
    }

    @GetMapping("/user/{userId}/messages")
    public ResponseEntity<String> getUserMessages(@PathVariable Long userId) {
        return ResponseEntity.ok(communityService.getUserMessages(userId));
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<String> updateGroup(@PathVariable Long id, @RequestBody String groupDetails) {
        return ResponseEntity.ok(communityService.updateGroup(id, groupDetails));
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.deleteGroup(id));
    }

    @PostMapping("/groups/{id}/members/{userId}")
    public ResponseEntity<String> addMemberToGroup(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(communityService.addMemberToGroup(id, userId));
    }

    @DeleteMapping("/groups/{id}/members/{userId}")
    public ResponseEntity<String> removeMemberFromGroup(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(communityService.removeMemberFromGroup(id, userId));
    }

    @GetMapping("/friends/requests")
    public ResponseEntity<String> getFriendRequests() {
        return ResponseEntity.ok(communityService.getFriendRequests());
    }

    @PostMapping("/friends/respond")
    public ResponseEntity<String> respondToFriendRequest(@RequestBody Long requestId, @RequestParam Boolean accept) {
        return ResponseEntity.ok(communityService.respondToFriendRequest(requestId, accept));
    }

    @GetMapping("/community/feed")
    public ResponseEntity<String> getCommunityFeed() {
        return ResponseEntity.ok(communityService.getCommunityFeed());
    }

    @PostMapping("/groups/{id}/events")
    public ResponseEntity<String> createGroupEvent(@PathVariable Long id, @RequestBody String eventData) {
        return ResponseEntity.ok(communityService.createGroupEvent(id, eventData));
    }

    @GetMapping("/groups/{id}/events")
    public ResponseEntity<String> getGroupEvents(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getGroupEvents(id));
    }

    @PostMapping("/groups/{id}/events/{eventId}/participate")
    public ResponseEntity<String> participateInEvent(@PathVariable Long id, @PathVariable Long eventId) {
        return ResponseEntity.ok(communityService.participateInEvent(eventId));
    }

    @PostMapping("/community/posts")
    public ResponseEntity<String> createCommunityPost(@RequestBody String postContent) {
        return ResponseEntity.ok(communityService.createCommunityPost(postContent));
    }

    @GetMapping("/community/posts")
    public ResponseEntity<String> getCommunityPosts() {
        return ResponseEntity.ok(communityService.getCommunityPosts());
    }

    @PostMapping("/community/posts/{postId}/like")
    public ResponseEntity<String> likeCommunityPost(@PathVariable Long postId) {
        return ResponseEntity.ok(communityService.likeCommunityPost(postId));
    }

    @PostMapping("/community/events")
    public ResponseEntity<String> createCommunityEvent(@RequestBody String eventDetails) {
        return ResponseEntity.ok(communityService.createCommunityEvent(eventDetails));
    }

    @PostMapping("/community/achievements")
    public ResponseEntity<String> shareUserAchievement(@RequestBody Long userId, @RequestBody String achievementDetails) {
        return ResponseEntity.ok(communityService.shareUserAchievement(userId, achievementDetails));
    }
}
