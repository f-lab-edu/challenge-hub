package daehee.challengehub.challenge.interaction.service;

import daehee.challengehub.challenge.interaction.entity.ChatMessage;
import daehee.challengehub.challenge.interaction.entity.ChatRoom;
import daehee.challengehub.challenge.interaction.entity.Review;
import daehee.challengehub.challenge.interaction.model.ChatMessageDto;
import daehee.challengehub.challenge.interaction.model.ReviewDto;
import daehee.challengehub.challenge.interaction.repository.InteractionRepository;
import daehee.challengehub.kafka.model.InteractionNotificationMessage;
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
        sendChatNotification(chatMessage);
        return chatMessage;
    }


    // 챌린지 채팅 메시지에 대한 알림을 보내는 로직
    // TODO: 임시 코드 수정 예정, return값 void로 한 거 고치기
    private void sendChatNotification(ChatMessage chatMessage) {
        ChatRoom chatRoom = interactionRepository.findChatRoomById(chatMessage.getChatRoomId());
        User sender = userRepository.findById(chatMessage.getSenderId());
        Challenge challenge = challengeRepository.findById(chatRoom.getChallengeId());

        InteractionNotificationMessage notificationMessage = InteractionNotificationMessage.builder()
                .userIds(chatRoom.getParticipants())
                .message(sender.getUsername() + "님이 '" + chatMessage.getContent() + "' - 챌린지 " + challenge.getTitle())
                .chatMessage(chatMessage.getContent())
                .challengeName(challenge.getTitle())
                .build();
        notificationService.sendNotification(notificationMessage);
    }

    // 특정 챌린지의 채팅방 내용을 조회하는 로직
    // TODO: 모든 거 전체 다 가져오는 것은 당연히 문제가 생길 것이다. 이것에 대해서 공부 해야한다.
    public List<ChatMessage> getChatMessages(String challengeId) {
        return interactionRepository.findChatMessagesByChallengeId(challengeId);
    }

    // 챌린지에 후기 및 별점을 작성하는 로직
    public Review postReview(String challengeId, ReviewDto reviewDto) {
        Review review = interactionRepository.saveReview(challengeId, reviewDto);
        sendReviewNotification(challengeId, reviewDto);
        return review;
    }

    // 챌린지 후기에 대한 알림을 보내는 로직
    private void sendReviewNotification(String challengeId, ReviewDto reviewDto) {
        InteractionNotificationMessage notificationMessage = InteractionNotificationMessage.builder()
                .userIds(/* 챌린지 리더의 ID 목록 */)
                .message("A new review and rating have been posted")
                .chatMessage(reviewDto.getReviewText())
                .challengeName(challengeId)
                .build();
        notificationService.sendNotification(notificationMessage);
    }

    // 특정 챌린지의 모든 후기 및 별점을 조회하는 로직
    // TODO: 모든 거 전체 다 가져오는 것은 당연히 문제가 생길 것이다. 이것에 대해서 공부 해야한다.
    public List<Review> getReviews(String challengeId) {
        return interactionRepository.findReviewsByChallengeId(challengeId);
    }
}
