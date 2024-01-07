package daehee.challengehub.service;

import daehee.challengehub.model.ManagementNotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// TODO: 에러 처리 관련된 내용은 Exception Handler를 통해서 api 모듈과 함께 처리 예정
@Service
public class ManagementNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String MANAGEMENT_TOPIC = "management-notifications";

    @Autowired
    public ManagementNotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    // 챌린지 추가 알림
    public boolean notifyChallengeCreation(String challengeName) {
        try {
            String notification = "새로운 챌린지 " + challengeName + "가 시작되었습니다! 관심 있으시면 지금 바로 확인해보세요!";
            kafkaTemplate.send(MANAGEMENT_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    // 챌린지 수정 알림
    public boolean notifyChallengeUpdate(String challengeName) {
        try {
            String notification = "참여하신 챌린지 " + challengeName + "에 중요한 변경사항이 있습니다. 확인해주세요!";
            kafkaTemplate.send(MANAGEMENT_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    // 챌린지 삭제 알림
    public boolean notifyChallengeDeletion(String challengeName) {
        try {
            String notification = "아쉽게도 참여하신 챌린지 " + challengeName + "가 종료되었습니다.";
            kafkaTemplate.send(MANAGEMENT_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    public boolean processManagementNotification(ManagementNotificationMessage message) {
        return switch (message.getType()) {
            case "추가" -> notifyChallengeCreation(message.getChallengeName());
            case "수정" -> notifyChallengeUpdate(message.getChallengeName());
            case "삭제" -> notifyChallengeDeletion(message.getChallengeName());
            default -> throw new IllegalArgumentException("알 수 없는 알림 유형: " + message.getType());
        };
    }
}
