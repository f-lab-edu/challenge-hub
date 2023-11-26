package daehee.challengehub.user.message.controller;

import daehee.challengehub.user.message.model.ChatRoomsResponseDto;
import daehee.challengehub.user.message.model.SendMessageResponseDto;
import daehee.challengehub.user.message.model.MessageHistoryResponseDto;
import daehee.challengehub.user.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user/{userId}")
    public SendMessageResponseDto sendMessage(@PathVariable Long userId, @RequestBody String messageContent) {
        return messageService.sendMessage(userId, messageContent);
    }

    @GetMapping("/user/{userId}/messages")
    public MessageHistoryResponseDto getMessageHistory(@PathVariable Long userId) {
        return messageService.getMessageHistory(userId);
    }

    @GetMapping("/user/rooms")
    public ChatRoomsResponseDto getChatRooms() {
        return messageService.getChatRooms();
    }
}
