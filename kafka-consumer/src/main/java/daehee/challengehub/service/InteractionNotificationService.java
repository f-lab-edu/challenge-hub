package daehee.challengehub.service;

import daehee.challengehub.model.InteractionNotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// TODO: 에러 처리 관련된 내용은 Exception Handler를 통해서 api 모듈과 함께 처리 예정
@Service
public class InteractionNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String INTERACTION_TOPIC = "interaction-notifications";

    @Autowired
    public InteractionNotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 챌린지 채팅방에 메시지 전송 알림
    public boolean notifyChatMessage(String username, String messageContent, String challengeName) {
        try {
            String notification = username + "님이 '" + messageContent + "' - 챌린지 " + challengeName;
            kafkaTemplate.send(INTERACTION_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    // 챌린지 후기 및 별점 작성 알림
    public boolean notifyReviewAndRating(String username, String challengeName) {
        try {
            String notification = username + "님이 챌린지 " + challengeName + "에 새로운 후기와 별점을 남겼습니다:";
            kafkaTemplate.send(INTERACTION_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    // Kafka 메시지를 처리하는 새로운 메소드
    public boolean processInteractionNotification(InteractionNotificationMessage message) {
        boolean result = true;

        if (!message.getChatMessage().isEmpty()) {
            for (String userName : message.getUserNames()) {
                result &= notifyChatMessage(userName, message.getChatMessage(), message.getChallengeName());
            }
        }

        if (!message.getUserIds().isEmpty() && !message.getUserNames().isEmpty()) {
            String leaderName = message.getUserNames().get(0);
            result &= notifyReviewAndRating(leaderName, message.getChallengeName());
        }

        return result;
    }
}
