package daehee.challengehub.message.controller;

import daehee.challengehub.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
