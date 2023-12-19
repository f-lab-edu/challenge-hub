package daehee.challengehub.user.message.service;

import daehee.challengehub.user.message.model.ChatRoomDto;
import daehee.challengehub.user.message.model.ChatRoomsResponseDto;
import daehee.challengehub.user.message.model.MessageDto;
import daehee.challengehub.user.message.model.MessageHistoryResponseDto;
import daehee.challengehub.user.message.model.SendMessageResponseDto;
import daehee.challengehub.user.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 개인 메시지 전송 로직
    public SendMessageResponseDto sendMessage(Long userId, String messageContent) {
        // 메시지 생성 및 저장
        MessageDto message = messageRepository.createAndSaveMessage(userId, 456L, messageContent); // 456L은 예시용 리시버 ID

        // SendMessageResponseDto 반환
        return new SendMessageResponseDto("메시지 전송 성공", message);
    }

    // 메시지 목록 조회 로직
    public MessageHistoryResponseDto getMessageHistory(Long userId) {
        List<MessageDto> messages = messageRepository.findMessagesByUserId(userId);

        // MessageHistoryResponseDto 반환
        return new MessageHistoryResponseDto(messages);
    }

    // 채팅방 목록 조회 로직
    public ChatRoomsResponseDto getChatRooms() {
        List<ChatRoomDto> chatRooms = messageRepository.findAllChatRooms();

        // ChatRoomsResponseDto 반환
        return new ChatRoomsResponseDto(chatRooms);
    }
}
