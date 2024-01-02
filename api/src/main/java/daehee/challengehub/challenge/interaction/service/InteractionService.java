package daehee.challengehub.challenge.interaction.service;

import daehee.challengehub.challenge.interaction.entity.ChatMessage;
import daehee.challengehub.challenge.interaction.entity.Review;
import daehee.challengehub.challenge.interaction.model.ChatMessageDto;
import daehee.challengehub.challenge.interaction.model.ReviewDto;
import daehee.challengehub.challenge.interaction.repository.InteractionRepository;
import daehee.challengehub.kafka.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {
    private final InteractionRepository interactionRepository;
    private final NotificationService notificationService;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository, NotificationService notificationService) {
        this.interactionRepository = interactionRepository;
        this.notificationService = notificationService;
    }


    // 챌린지 채팅방의 메시지를 전송하는 로직
    public ChatMessage postChatMessage(String challengeId, ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = interactionRepository.saveChatMessage(challengeId, chatMessageDto);
        return chatMessage;
    }

    // 특정 챌린지의 채팅방 내용을 조회하는 로직
    // TODO: 모든 거 전체 다 가져오는 것은 당연히 문제가 생길 것이다. 이것에 대해서 공부 해야한다.
    public List<ChatMessage> getChatMessages(String challengeId) {
        return interactionRepository.findChatMessagesByChallengeId(challengeId);
    }

    // 챌린지에 후기 및 별점을 작성하는 로직
    public Review postReview(String challengeId, ReviewDto reviewDto) {
        Review review = interactionRepository.saveReview(challengeId, reviewDto);
        return review;
    }

    // 특정 챌린지의 모든 후기 및 별점을 조회하는 로직
    // TODO: 모든 거 전체 다 가져오는 것은 당연히 문제가 생길 것이다. 이것에 대해서 공부 해야한다.
    public List<Review> getReviews(String challengeId) {
        return interactionRepository.findReviewsByChallengeId(challengeId);
    }
}
