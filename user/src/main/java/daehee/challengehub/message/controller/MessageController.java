package daehee.challengehub.message.controller;

import daehee.challengehub.message.model.ChatRoomDto;
import daehee.challengehub.message.model.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/message")
public class MessageController {

    // 다른 사용자에게 개인 메시지 전송
    @PostMapping("/user/{userId}")
    public Map<String, Object> sendMessage(@PathVariable Long userId, @RequestBody String messageContent) {
        // 임의의 메시지 데이터 생성
        MessageDto message = MessageDto.builder()
                .messageId(1L)
                .senderId(123L)
                .receiverId(456L)
                .messageContent("안녕하세요. 만나서 반갑습니다.")
                .sentTime("2023-04-05T15:00:00Z")
                .isRead(false)
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "메시지 전송 성공");
        response.put("sentMessage", message);
        return response;
    }


    // 다른 사용자와 나눈 메시지 목록 조회
    @GetMapping("/user/{userId}/messages")
    public Map<String, Object> getMessageHistory(@PathVariable Long userId) {
        // 임의의 메시지 목록 데이터 생성
        List<MessageDto> messages = Arrays.asList(
                MessageDto.builder()
                        .messageId(1L)
                        .senderId(123L)
                        .receiverId(456L)
                        .messageContent("안녕하세요.")
                        .sentTime("2023-04-05T15:00:00Z")
                        .isRead(true)
                        .build(),
                MessageDto.builder()
                        .messageId(2L)
                        .senderId(789L)
                        .receiverId(123L)
                        .messageContent("만나서 반갑습니다.")
                        .sentTime("2023-04-05T16:00:00Z")
                        .isRead(false)
                        .build()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "메시지 목록 조회 성공");
        response.put("messages", messages);
        return response;
    }


    // 사용자가 참여하고 있는 채팅방 목록 조회
    @GetMapping("/user/rooms")
    public Map<String, Object> getChatRooms() {
        // Builder 패턴을 사용한 채팅방 목록 데이터 생성
        List<ChatRoomDto> chatRooms = Arrays.asList(
                ChatRoomDto.builder()
                        .roomId(1L)
                        .roomName("Room 1")
                        .lastMessageId(2L)
                        .lastMessagePreview("Hi there!")
                        .lastMessageTime("2023-04-05T16:00:00Z")
                        .unreadMessagesCount(1)
                        .roomImageUrl(null)
                        .build(),
                ChatRoomDto.builder()
                        .roomId(2L)
                        .roomName("Room 2")
                        .lastMessageId(3L)
                        .lastMessagePreview("How are you?")
                        .lastMessageTime("2023-04-06T10:00:00Z")
                        .unreadMessagesCount(2)
                        .roomImageUrl(null)
                        .build()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "채팅방 목록 조회 성공");
        response.put("chatRooms", chatRooms);
        return response;
    }
}
