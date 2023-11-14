package daehee.challengehub.controller;

import daehee.challengehub.model.*;
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


    // 사용자 팔로우
    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(@PathVariable Long userId) {
        FollowDto newFollow = FollowDto.builder()
                .followerId(123L)
                .followingId(456L)
                .followDate("2023-11-15")
                .isMutual(true)
                .build();

        String responseMessage = String.format("사용자 %d 팔로우 성공. 상호 팔로우 상태: %s",
                userId, newFollow.isMutual() ? "예" : "아니오");
        return ResponseEntity.ok(responseMessage);
    }


    // 팔로워 목록 조회
    @GetMapping("/followers")
    public ResponseEntity<List<FollowDto>> getFollowersList() {
        List<FollowDto> followersList = Arrays.asList(
                FollowDto.builder()
                        .followerId(123L)
                        .followingId(456L)
                        .followDate("2023-01-01")
                        .isMutual(true)
                        .build(),
                FollowDto.builder()
                        .followerId(789L)
                        .followingId(456L)
                        .followDate("2023-02-01")
                        .isMutual(false)
                        .build()
        );

        return ResponseEntity.ok(followersList);
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


    // TODO: FriendRequestDto 구현해야할 듯, Friend 관련 컨트롤러도 분리가 필요해보임
    // 친구 요청 목록 조회
//    @GetMapping("/friends/requests")
//    public ResponseEntity<List<FriendRequestDto>> getFriendRequests() {
//        // 임의의 친구 요청 데이터 생성
//        List<FriendRequestDto> friendRequests = Arrays.asList(
//                FriendRequestDto.builder()
//                        .requesterId(101L) // 요청자 ID
//                        .requestedId(202L) // 요청 받은 사용자 ID
//                        .requestDate("2023-11-10") // 요청 날짜
//                        .status("대기 중") // 요청 상태
//                        .build(),
//                FriendRequestDto.builder()
//                        .requesterId(103L) // 다른 요청자 ID
//                        .requestedId(202L) // 같은 요청 받은 사용자 ID
//                        .requestDate("2023-11-09") // 요청 날짜
//                        .status("대기 중") // 요청 상태
//                        .build()
//        );
//        return ResponseEntity.ok(friendRequests);
//    }

    // TODO: FriendDto 확장해서 구현해야할 듯, Friend 관련 컨트롤러도 분리가 필요해보임
//    // 친구 요청 응답
//    @PostMapping("/friends/respond")
//    public ResponseEntity<String> respondToFriendRequest(@RequestBody Long requestId, @RequestParam Boolean accept) {
//        return ResponseEntity.ok(communityService.respondToFriendRequest(requestId, accept));
//    }


    // 커뮤니티 피드 조회
    @GetMapping("/community/feed")
    public ResponseEntity<List<CommunityPostDto>> getCommunityFeed() {
        // 임의의 커뮤니티 피드 데이터 생성
        List<CommunityPostDto> communityFeed = Arrays.asList(
                CommunityPostDto.builder()
                        .postId(1L)
                        .authorId(100L)
                        .postContent("첫 번째 커뮤니티 포스트 내용")
                        .postTitle("첫 번째 커뮤니티 포스트")
                        .creationDate("2023-11-10")
                        .lastEdited("2023-11-10")
                        .likeCount(15)
                        .commentCount(4)
                        .build(),
                CommunityPostDto.builder()
                        .postId(2L)
                        .authorId(101L)
                        .postContent("두 번째 커뮤니티 포스트 내용")
                        .postTitle("두 번째 커뮤니티 포스트")
                        .creationDate("2023-11-11")
                        .lastEdited("2023-11-11")
                        .likeCount(10)
                        .commentCount(2)
                        .build()
        );

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
}
