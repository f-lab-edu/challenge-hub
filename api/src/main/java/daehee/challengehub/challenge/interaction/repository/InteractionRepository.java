package daehee.challengehub.challenge.interaction.repository;

import daehee.challengehub.challenge.interaction.entity.ChatMessage;
import daehee.challengehub.challenge.interaction.entity.ChatRoom;
import daehee.challengehub.challenge.interaction.entity.Review;
import daehee.challengehub.challenge.interaction.model.ChatMessageDto;
import daehee.challengehub.challenge.interaction.model.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class InteractionRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public InteractionRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ChatMessage saveChatMessage(String challengeId, ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = ChatMessage.builder()
                .challengeId(challengeId)
                .senderId(chatMessageDto.getSenderId())
                .content(chatMessageDto.getContent())
                .timestamp(Instant.now())
                .build();
        return mongoTemplate.save(chatMessage, "chat_messages");
    }

    // 챌린지 ID에 따른 채팅 메시지 조회 로직
    public List<ChatMessage> findChatMessagesByChallengeId(String challengeId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId));
        return mongoTemplate.find(query, ChatMessage.class, "chat_messages");
    }

    public ChatRoom findChatRoomById(String challengeId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId));
        return mongoTemplate.findOne(query, ChatRoom.class, "chat_rooms");
    }

    public Review saveReview(String challengeId, ReviewDto reviewDto) {
        Review review = Review.builder()
                .challengeId(challengeId)
                .authorId(reviewDto.getAuthorId())
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .createdDate(Instant.now())
                .build();
        return mongoTemplate.save(review, "reviews");
    }

    public List<Review> findReviewsByChallengeId(String challengeId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId));
        return mongoTemplate.find(query, Review.class, "reviews");
    }
}

