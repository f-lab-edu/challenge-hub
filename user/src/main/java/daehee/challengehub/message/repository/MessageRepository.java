package daehee.challengehub.message.repository;

import daehee.challengehub.message.model.ChatRoomDto;
import daehee.challengehub.message.model.MessageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessageRepository {
    private final Map<Long, MessageDto> messages = new HashMap<>();
    private final Map<Long, ChatRoomDto> chatRooms = new HashMap<>();

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
        List<MessageDto> userMessages = new ArrayList<>();
        for (MessageDto message : messages.values()) {
            if (message.getSenderId().equals(userId) || message.getReceiverId().equals(userId)) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    // 채팅방 저장
    public void saveChatRoom(ChatRoomDto chatRoom) {
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
    }

    // 모든 채팅방 조회
    public List<ChatRoomDto> findAllChatRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    // 채팅방별 마지막 메시지 조회
    public MessageDto findLastMessageInChatRoom(Long roomId) {
        // 여기에 채팅방별 마지막 메시지를 조회하는 로직 구현
        // 예시 코드에서는 구현되지 않았습니다.
        return null;
    }
}
