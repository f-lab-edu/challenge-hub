package daehee.challengehub.controller;

import daehee.challengehub.model.*;
import daehee.challengehub.network.model.FollowDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CommunityInteractionController {
    // 친구 추가
    @PostMapping("/friends")
    public ResponseEntity<String> addFriend(@RequestBody FriendDto friendDto) {
        FriendDto newFriend = FriendDto.builder()
                .userId(123L)
                .friendId(456L)
                .friendshipStartDate("2023-11-11")
                .build();

        String responseMessage = String.format("사용자 ID %d가 사용자 ID %d를 친구로 추가함", newFriend.getUserId(), newFriend.getFriendId());
        return ResponseEntity.ok(responseMessage);
    }


    // 친구 목록 조회
    @GetMapping("/friends")
    public ResponseEntity<List<FriendDto>> getFriendsList() {
        List<FriendDto> friendsList = Arrays.asList(
                FriendDto.builder()
                        .userId(123L)
                        .friendId(456L)
                        .friendshipStartDate("2023-01-01")
                        .build(),
                FriendDto.builder()
                        .userId(123L)
                        .friendId(789L)
                        .friendshipStartDate("2023-02-01")
                        .build()
        );

        return ResponseEntity.ok(friendsList);
    }



    // 다른 사용자의 프로필 조회
    @GetMapping("/user/{userId}/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long userId) {
        UserProfileDto userProfile = UserProfileDto.builder()
                .username("user123")
                .email("user123@example.com")
                .bio("이곳에 사용자 바이오")
                .build();

        return ResponseEntity.ok(userProfile);
    }


    // 다른 사용자에게 개인 메시지 전송
    @PostMapping("/user/{userId}/message")
    public ResponseEntity<String> sendMessageToUser(@PathVariable Long userId, @RequestBody MessageDto messageDto) {
        MessageDto newMessage = MessageDto.builder()
                .senderId(1L)
                .receiverId(2L)
                .messageContent("안녕하세요, 메시지를 보냅니다!")
                .sentTime("2023-11-13T10:00:00")
                .isRead(false)
                .build();

        String responseMessage = String.format("사용자 %d에게 메시지 전송 성공: %s", userId, newMessage.getMessageContent());
        return ResponseEntity.ok(responseMessage);
    }


    // 개인 메시지 목록 조회
    @GetMapping("/user/{userId}/messages")
    public ResponseEntity<List<MessageDto>> getUserMessages(@PathVariable Long userId) {
        List<MessageDto> messagesList = Arrays.asList(
                MessageDto.builder()
                        .senderId(2L)
                        .receiverId(3L)
                        .messageContent("첫 번째 메시지 내용")
                        .sentTime("2023-11-13T09:00:00")
                        .isRead(true)
                        .build(),
                MessageDto.builder()
                        .senderId(3L)
                        .receiverId(4L)
                        .messageContent("두 번째 메시지 내용")
                        .sentTime("2023-11-14T11:00:00")
                        .isRead(false)
                        .build()
        );

        return ResponseEntity.ok(messagesList);
    }





<<<<<<< HEAD:community-interaction/src/main/java/daehee/challengehub/controller/CommunityInteractionController.java
        return ResponseEntity.ok(communityFeed);
    }


    // 커뮤니티 포스트 작성
    @PostMapping("/community/posts")
    public ResponseEntity<String> createCommunityPost(@RequestBody CommunityPostDto communityPostDto) {
        CommunityPostDto newPost = CommunityPostDto.builder()
                .postId(1L) // 임의의 게시물 ID
                .authorId(communityPostDto.getAuthorId()) // 작성자 ID
                .postContent(communityPostDto.getPostContent()) // 게시물 내용
                .postTitle(communityPostDto.getPostTitle()) // 게시물 제목
                .creationDate("2023-11-15") // 게시 날짜
                .lastEdited("2023-11-15") // 마지막 수정 날짜
                .likeCount(0) // 좋아요 수
                .commentCount(0) // 댓글 수
                .build();

        String responseMessage = String.format("커뮤니티 포스트 생성 성공: %s", newPost.getPostTitle());
        return ResponseEntity.ok(responseMessage);
    }


    // 커뮤니티 포스트 목록 조회
    @GetMapping("/community/posts")
    public ResponseEntity<List<CommunityPostDto>> getCommunityPosts() {
        List<CommunityPostDto> posts = Arrays.asList(
                CommunityPostDto.builder()
                        .postId(1L)
                        .authorId(101L)
                        .postContent("첫 번째 포스트 내용")
                        .postTitle("첫 번째 포스트")
                        .creationDate("2023-11-15")
                        .lastEdited("2023-11-15")
                        .likeCount(10)
                        .commentCount(5)
                        .build(),
                CommunityPostDto.builder()
                        .postId(2L)
                        .authorId(102L)
                        .postContent("두 번째 포스트 내용")
                        .postTitle("두 번째 포스트")
                        .creationDate("2023-11-14")
                        .lastEdited("2023-11-14")
                        .likeCount(7)
                        .commentCount(3)
                        .build()
        );

        return ResponseEntity.ok(posts);
    }


    // 포스트 좋아요
    @PostMapping("/community/posts/{postId}/like")
    public ResponseEntity<String> likeCommunityPost(@PathVariable Long postId) {
        CommunityPostDto likedPost = CommunityPostDto.builder()
                .postId(postId)
                .authorId(101L) // 임의의 작성자 ID
                .postContent("이 포스트는 좋아요를 받았습니다.")
                .postTitle("좋아요 받은 포스트")
                .creationDate("2023-11-15")
                .lastEdited("2023-11-15")
                .likeCount(11) // 좋아요 수 증가
                .commentCount(5)
                .build();

        String responseMessage = String.format("포스트 ID %d에 좋아요 성공", postId);
        return ResponseEntity.ok(responseMessage);
    }


    // 사용자들의 챌린지나 활동에서의 성취 공유
    @PostMapping("/community/achievements")
    public ResponseEntity<String> shareUserAchievement(@RequestBody AchievementDto achievementDto) {
        // 임의의 사용자 성취 데이터 생성
        AchievementDto userAchievement = AchievementDto.builder()
                .userId(achievementDto.getUserId()) // 사용자 ID
                .challengeId(achievementDto.getChallengeId()) // 챌린지 ID
                .achievementDetails("새로운 챌린지 완료!") // 성취 내용
                .achievedDate("2023-11-15") // 성취한 날짜
                .build();

        String responseMessage = String.format("사용자 ID %d: '%s' 성취 공유 성공",
                userAchievement.getUserId(),
                userAchievement.getAchievementDetails());
        return ResponseEntity.ok(responseMessage);
    }

=======
>>>>>>> a1676ae (ADD: CommunityController.java 작성 완료):social/src/main/java/daehee/challengehub/controller/CommunityInteractionController.java
}
