package user.service;


import daehee.challengehub.user.message.model.ChatRoomDto;
import daehee.challengehub.user.message.model.ChatRoomsResponseDto;
import daehee.challengehub.user.message.model.MessageDto;
import daehee.challengehub.user.message.model.MessageHistoryResponseDto;
import daehee.challengehub.user.message.model.SendMessageResponseDto;
import daehee.challengehub.user.message.repository.MessageRepository;
import daehee.challengehub.user.message.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    public void sendMessage_Success() {
        Long userId = 123L;
        String messageContent = "Hello!";
        MessageDto mockMessage = new MessageDto(1L, userId, 456L, messageContent, Instant.parse("2023-04-05T15:00:00Z"), false, null, null, "text");
        when(messageRepository.createAndSaveMessage(eq(userId), anyLong(), eq(messageContent))).thenReturn(mockMessage);

        SendMessageResponseDto response = messageService.sendMessage(userId, messageContent);

        assertEquals("메시지 전송 성공", response.getStatus());
        assertEquals(mockMessage, response.getMessage());
    }

    @Test
    public void getMessageHistory_Success() {
        Long userId = 123L;
        List<MessageDto> mockMessages = List.of(
                new MessageDto(1L, userId, 456L, "Hi", Instant.parse("2023-04-05T15:00:00Z"), true, null, null, "text"),
                new MessageDto(2L, 456L, userId, "Hello", Instant.parse("2023-04-05T16:00:00Z"), false, null, null, "text")
        );
        when(messageRepository.findMessagesByUserId(userId)).thenReturn(mockMessages);

        MessageHistoryResponseDto response = messageService.getMessageHistory(userId);

        // TODO: message 필드를 굳이 추가를 해야할까?
//        assertEquals("메시지 목록 조회 성공", response.getMessage());
        assertEquals(mockMessages, response.getMessages());
    }

    @Test
    public void getChatRooms_Success() {
        List<ChatRoomDto> mockChatRooms = List.of(
                new ChatRoomDto(1L, "Room 1", 2L, "Hi there!", Instant.parse("2023-04-05T16:00:00Z"), 1, null),
                new ChatRoomDto(2L, "Room 2", 3L, "How are you?", Instant.parse("2023-04-06T10:00:00Z"), 2, null)
        );
        when(messageRepository.findAllChatRooms()).thenReturn(mockChatRooms);

        ChatRoomsResponseDto response = messageService.getChatRooms();

        // TODO: message 필드를 굳이 추가를 해야할까?
//        assertEquals("채팅방 목록 조회 성공", response.getMessage());
        assertEquals(mockChatRooms, response.getChatRooms());
    }
}
