package daehee.challengehub.user.message.controller;

import daehee.challengehub.user.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user/{userId}")
    public Map<String, Object> sendMessage(@PathVariable Long userId, @RequestBody String messageContent) {
        return messageService.sendMessage(userId, messageContent);
    }

    @GetMapping("/user/{userId}/messages")
    public Map<String, Object> getMessageHistory(@PathVariable Long userId) {
        return messageService.getMessageHistory(userId);
    }

    @GetMapping("/user/rooms")
    public Map<String, Object> getChatRooms() {
        return messageService.getChatRooms();
    }
}
