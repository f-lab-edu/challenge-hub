package daehee.challengehub.challenge.interaction.controller;

import daehee.challengehub.challenge.interaction.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/challenges")
public class InteractionController {
    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    // 챌린지 채팅방의 메시지를 전송하는 API
    @PostMapping("/{id}/chat/message")
    public ResponseEntity<?> postChatMessage(@PathVariable Long id, @RequestBody ChatMessageDto chatMessageDto) {
        interactionService.postChatMessage(id, chatMessageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 특정 챌린지의 채팅방 내용을 조회하는 API
    @GetMapping("/{id}/chat")
    public List<ChatMessageDto> getChatMessages(@PathVariable Long id) {
        return interactionService.getChatMessages(id);
    }

    // 챌린지에 후기 및 별점을 작성하는 API
    @PostMapping("/{id}/review")
    public ResponseEntity<?> postReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        interactionService.postReview(id, reviewDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 특정 챌린지의 모든 후기 및 별점을 조회하는 API
    @GetMapping("/{id}/reviews")
    public List<ReviewDto> getReviews(@PathVariable Long id) {
        return interactionService.getReviews(id);
    }
}
