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





}
