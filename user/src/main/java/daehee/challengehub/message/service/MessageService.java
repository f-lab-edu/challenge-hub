package daehee.challengehub.message.service;

import daehee.challengehub.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daehee.challengehub.message.model.ChatRoomDto;
import daehee.challengehub.message.model.MessageDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 개인 메시지 전송 로직
    public Map<String, Object> sendMessage(Long userId, String messageContent) {
        MessageDto message = MessageDto.builder()
                .messageId(1L)
                .senderId(123L)
                .receiverId(456L)
                .messageContent("안녕하세요. 만나서 반갑습니다.")
                .sentTime("2023-04-05T15:00:00Z")
                .isRead(false)
                .build();

        // 메시지 저장
        messageRepository.saveMessage(message);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "메시지 전송 성공");
        response.put("sentMessage", message);
        return response;
    }

    // 메시지 목록 조회 로직
    public Map<String, Object> getMessageHistory(Long userId) {
        // TODO: Repository 로직 구현이 제대로 완료되면 주석 풀 예정
//        List<MessageDto> messages = messageRepository.findMessagesByUserId(userId);
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

    // 채팅방 목록 조회 로직
    public Map<String, Object> getChatRooms() {
        // TODO: Repository 로직 구현이 제대로 완료되면 주석 풀 예정
//        List<ChatRoomDto> chatRooms = messageRepository.findAllChatRooms();
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
