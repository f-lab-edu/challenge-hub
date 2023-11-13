package daehee.challengehub.controller;

import daehee.challengehub.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    // 챌린지 그룹 생성
    @PostMapping("/groups")
    public ResponseEntity<String> createGroup(@RequestBody GroupDto groupDto) {
        GroupDto newGroup = GroupDto.builder()
                .groupId(1L)
                .groupName("챌린지 그룹123")
                .groupDescription("새로운 챌린지 그룹 설명")
                .creatorId(123L)
                .creationDate("2023-11-15")
                .build();

        String responseMessage = String.format("그룹 '%s' 생성 성공", newGroup.getGroupName());
        return ResponseEntity.ok(responseMessage);
    }


    // 특정 그룹 상세 조회
    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupDto> getGroupDetails(@PathVariable Long id) {
        GroupDto groupDetails = GroupDto.builder()
                .groupId(id)
                .groupName("샘플 그룹")
                .groupDescription("이것은 샘플 그룹에 대한 설명입니다.")
                .creatorId(123L)
                .creationDate("2023-11-15")
                .build();

        return ResponseEntity.ok(groupDetails);
    }


    // 그룹 메시지 전송
    @PostMapping("/groups/{id}/messages")
    public ResponseEntity<String> sendMessageToGroup(@PathVariable Long id, @RequestBody MessageDto messageDto) {
        MessageDto newMessage = MessageDto.builder()
                .senderId(123L)
                .receiverId(id)
                .messageContent("샘플 그룹 메시지")
                .sentTime("2023-11-15 10:00:00")
                .isRead(false)
                .build();

        String responseMessage = String.format("그룹 ID %d에 메시지 전송 성공: %s", id, newMessage.getMessageContent());
        return ResponseEntity.ok(responseMessage);
    }


    // 그룹 내 메시지 조회
    @GetMapping("/groups/{id}/messages")
    public ResponseEntity<List<MessageDto>> getGroupMessages(@PathVariable Long id) {
        List<MessageDto> messagesList = Arrays.asList(
                MessageDto.builder()
                        .senderId(123L)
                        .receiverId(id)
                        .messageContent("첫 번째 그룹 메시지")
                        .sentTime("2023-11-15 09:00:00")
                        .isRead(true)
                        .build(),
                MessageDto.builder()
                        .senderId(456L)
                        .receiverId(id)
                        .messageContent("두 번째 그룹 메시지")
                        .sentTime("2023-11-15 09:30:00")
                        .isRead(false)
                        .build()
        );

        return ResponseEntity.ok(messagesList);
    }



    // 다른 사용자의 프로필 조회
    @GetMapping("/user/{userId}/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long userId) {
        UserProfileDto userProfile = UserProfileDto.builder()
                .username("user123")
                .email("user123@example.com")
                .bio("이곳에 사용자 바이오")
                .location("서울")
                .build();

        return ResponseEntity.ok(userProfile);
    }


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



    // 그룹 정보 수정
    @PutMapping("/groups/{id}")
    public ResponseEntity<String> updateGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        GroupDto updatedGroup = GroupDto.builder()
                .groupId(1L)
                .groupName("새로운 그룹 이름")
                .groupDescription("새로운 그룹 설명")
                .creatorId(123L)
                .creationDate("2023-01-01")
                .build();

        String responseMessage = String.format("그룹 ID %d 업데이트 성공: %s, %s",
                id, updatedGroup.getGroupName(), updatedGroup.getGroupDescription());
        return ResponseEntity.ok(responseMessage);
    }


    // 그룹 삭제
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        GroupDto groupToDelete = GroupDto.builder()
                .groupId(1L)
                .build();

        String responseMessage = String.format("그룹 ID %d 삭제 성공", groupToDelete.getGroupId());
        return ResponseEntity.ok(responseMessage);
    }


    // 그룹에 멤버 추가
    // TODO: 그룹에 멤버 구현하는 로직 자체가 완성이 덜 되었는데 분리 시키는 거 고려해보기
    @PostMapping("/groups/{id}/members/{userId}")
    public ResponseEntity<String> addMemberToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        // 임의의 그룹과 멤버 정보 생성
        GroupDto group = GroupDto.builder()
                .groupId(groupId)
                .groupName("챌린지 그룹")
                .groupDescription("이 그룹은 챌린지 참가자들을 위한 공간입니다.")
                .creatorId(123L)
                .creationDate("2023-01-01")
                .build();

        String responseMessage = String.format("멤버 ID %d가 그룹 ID %d에 추가되었습니다.", userId, group.getGroupId());
        return ResponseEntity.ok(responseMessage);
    }


    // TODO: GroupMemberRemovalDto 구현해야할 듯, GroupMemberRemovalDto 관련 컨트롤러도 분리가 필요해보임
    // 그룹에서 멤버 제거
//    @DeleteMapping("/groups/{id}/members/{userId}")
//    public ResponseEntity<String> removeMemberFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
//        // 임의의 그룹 멤버 제거 결과 데이터 생성
//        GroupMemberRemovalDto removalResult = GroupMemberRemovalDto.builder()
//                .groupId(groupId) // 그룹 ID
//                .userId(userId) // 제거된 멤버의 사용자 ID
//                .removalDate("2023-11-10") // 멤버 제거 날짜
//                .wasSuccessful(true) // 제거 성공 여부
//                .build();
//
//        String responseMessage = String.format("그룹 ID %d에서 사용자 ID %d의 멤버 제거 성공", groupId, userId);
//        return ResponseEntity.ok(responseMessage);
//    }

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


    // TODO: 제거 예정
//    // 그룹 이벤트 생성
//    @PostMapping("/groups/{id}/events")
//    public ResponseEntity<String> createGroupEvent(@PathVariable Long id, @RequestBody String eventData) {
//        return ResponseEntity.ok(communityService.createGroupEvent(id, eventData));
//    }

    // TODO: 제거 예정
//    // 그룹 이벤트 목록 조회
//    @GetMapping("/groups/{id}/events")
//    public ResponseEntity<String> getGroupEvents(@PathVariable Long id) {
//        return ResponseEntity.ok(communityService.getGroupEvents(id));
//    }


    // TODO: 제거 예정
//    // 이벤트 참여
//    @PostMapping("/groups/{id}/events/{eventId}/participate")
//    public ResponseEntity<String> participateInEvent(@PathVariable Long id, @PathVariable Long eventId) {
//        return ResponseEntity.ok(communityService.participateInEvent(eventId));
//    }


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


    // TODO: 제거 예정
//    // 오프라인 또는 온라인 모임, 이벤트 생성 및 관리
//    @PostMapping("/community/events")
//    public ResponseEntity<String> createCommunityEvent(@RequestBody String eventDetails) {
//        return ResponseEntity.ok(communityService.createCommunityEvent(eventDetails));
//    }


    // 사용자들의 챌린지나 활동에서의 성취 공유
    @PostMapping("/community/achievements")
    public ResponseEntity<String> shareUserAchievement(@RequestBody AchievementDto achievementDto) {
        // 임의의 사용자 성취 데이터 생성
        AchievementDto userAchievement = AchievementDto.builder()
                .userId(achievementDto.getUserId()) // 사용자 ID
                .challengeId(achievementDto.getChallengeId()) // 챌린지 ID
                .achievementDetails("새로운 챌린지 완료!") // 성취 내용
                .achievedDate("2023-11-15") // 성취한 날짜
                .isVerified(true) // 성취 인증 여부
                .build();

        String responseMessage = String.format("사용자 ID %d: '%s' 성취 공유 성공",
                userAchievement.getUserId(),
                userAchievement.getAchievementDetails());
        return ResponseEntity.ok(responseMessage);
    }

}
