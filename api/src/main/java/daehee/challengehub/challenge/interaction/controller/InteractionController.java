package daehee.challengehub.challenge.interaction.controller;

import daehee.challengehub.challenge.interaction.entity.ChatMessage;
import daehee.challengehub.challenge.interaction.entity.Review;
import daehee.challengehub.challenge.interaction.model.ChatMessageDto;
import daehee.challengehub.challenge.interaction.model.ReviewDto;
import daehee.challengehub.challenge.interaction.service.InteractionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class InteractionController {
    private final InteractionService interactionService;
    private final ModelMapper modelMapper;

    @Autowired
    public InteractionController(InteractionService interactionService, ModelMapper modelMapper) {
        this.interactionService = interactionService;
        this.modelMapper = modelMapper;
    }

    // 챌린지 채팅방의 메시지를 전송하는 API
    @PostMapping("/{id}/chat/message")
    public ChatMessageDto postChatMessage(@PathVariable String id, @RequestBody ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = interactionService.postChatMessage(id, chatMessageDto);
        return modelMapper.map(chatMessage, ChatMessageDto.class);
    }

    // 특정 챌린지의 채팅방 내용을 조회하는 API
    // TODO: 모든 거 전체 다 가져오는 것은 당연히 문제가 생길 것이다. 이것에 대해서 공부 해야한다.
    @GetMapping("/{id}/chat")
    public List<ChatMessageDto> getChatMessages(@PathVariable String id) {
        List<ChatMessage> chatMessages = interactionService.getChatMessages(id);
        return chatMessages.stream()
                .map(message -> modelMapper.map(message, ChatMessageDto.class))
                .toList();
    }

    // 챌린지에 후기 및 별점을 작성하는 API
    @PostMapping("/{id}/review")
    public ReviewDto postReview(@PathVariable String id, @RequestBody ReviewDto reviewDto) {
        Review review = interactionService.postReview(id, reviewDto);
        return modelMapper.map(review, ReviewDto.class);
    }

    // 특정 챌린지의 모든 후기 및 별점을 조회하는 API
    // TODO: 모든 거 전체 다 가져오는 것은 당연히 문제가 생길 것이다. 이것에 대해서 공부 해야한다.
    @GetMapping("/{id}/reviews")
    public List<ReviewDto> getReviews(@PathVariable String id) {
        List<Review> reviews = interactionService.getReviews(id);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .toList();
    }
}

