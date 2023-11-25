package daehee.challengehub.user.message.repository;

import daehee.challengehub.user.message.model.ChatRoomDto;
import daehee.challengehub.user.message.model.MessageDto;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MessageRepository {
    private final Map<Long, MessageDto> messages = new HashMap<>();
    private final Map<Long, ChatRoomDto> chatRooms = new HashMap<>();
    private long messageIdCounter = 1;

    public MessageRepository() {
        // 초기 메시지 데이터
        MessageDto message1 = MessageDto.builder()
                .messageId(1L)
                .senderId(123L)
                .receiverId(456L)
                .messageContent("안녕하세요.")
                .sentTime("2023-04-05T15:00:00Z")
                .isRead(true)
                .build();
        messages.put(message1.getMessageId(), message1);

        MessageDto message2 = MessageDto.builder()
                .messageId(2L)
                .senderId(789L)
                .receiverId(123L)
                .messageContent("만나서 반갑습니다.")
                .sentTime("2023-04-05T16:00:00Z")
                .isRead(false)
                .build();
        messages.put(message2.getMessageId(), message2);

        ChatRoomDto room1 = ChatRoomDto.builder()
                .roomId(1L)
                .roomName("Room 1")
                .lastMessageId(2L)
                .lastMessagePreview("Hi there!")
                .lastMessageTime("2023-04-05T16:00:00Z")
                .unreadMessagesCount(1)
                .roomImageUrl(null)
                .build();
        chatRooms.put(room1.getRoomId(), room1);

        ChatRoomDto room2 = ChatRoomDto.builder()
                .roomId(2L)
                .roomName("Room 2")
                .lastMessageId(3L)
                .lastMessagePreview("How are you?")
                .lastMessageTime("2023-04-06T10:00:00Z")
                .unreadMessagesCount(2)
                .roomImageUrl(null)
                .build();
        chatRooms.put(room2.getRoomId(), room2);
    }

    // 메시지 생성 및 저장
    public MessageDto createAndSaveMessage(Long senderId, Long receiverId, String messageContent) {
        MessageDto message = MessageDto.builder()
                .messageId(messageIdCounter++)
                .senderId(senderId)
                .receiverId(receiverId)
                .messageContent(messageContent)
                .sentTime(Instant.now().toString())
                .isRead(false)
                .build();
        messages.put(message.getMessageId(), message);
        return message;
    }

    // 메시지 저장
    public void saveMessage(MessageDto message) {
        messages.put(message.getMessageId(), message);
    }

    // 메시지 조회
    public MessageDto findMessageById(Long messageId) {
        return messages.get(messageId);
    }

    // 사용자별 메시지 목록 조회
    public List<MessageDto> findMessagesByUserId(Long userId) {
        return messages.values().stream()
                .filter(message -> message.getSenderId().equals(userId) || message.getReceiverId().equals(userId))
                .collect(Collectors.toList());
    }

    // 채팅방 저장
    public void saveChatRoom(ChatRoomDto chatRoom) {
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
    }

    // 모든 채팅방 조회
    public List<ChatRoomDto> findAllChatRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    // 채팅방별 마지막 메시지 조회 (예시)
    public MessageDto findLastMessageInChatRoom(Long roomId) {
        // 여기에 채팅방별 마지막 메시지를 조회하는 로직 구현
        // 예시 코드에서는 구현되지 않았습니다.
        return null;
    }
}
