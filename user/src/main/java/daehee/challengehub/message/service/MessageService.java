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
        // 메시지 생성 및 저장
        MessageDto message = messageRepository.createAndSaveMessage(userId, 456L, messageContent); // 456L은 예시용 리시버 ID

        Map<String, Object> response = new HashMap<>();
        response.put("message", "메시지 전송 성공");
        response.put("sentMessage", message);
        return response;
    }

    // 메시지 목록 조회 로직
    public Map<String, Object> getMessageHistory(Long userId) {
        List<MessageDto> messages = messageRepository.findMessagesByUserId(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "메시지 목록 조회 성공");
        response.put("messages", messages);
        return response;
    }

    // 채팅방 목록 조회 로직
    public Map<String, Object> getChatRooms() {
        List<ChatRoomDto> chatRooms = messageRepository.findAllChatRooms();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "채팅방 목록 조회 성공");
        response.put("chatRooms", chatRooms);
        return response;
    }
}
