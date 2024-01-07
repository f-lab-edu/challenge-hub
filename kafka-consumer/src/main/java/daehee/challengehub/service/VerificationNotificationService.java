package daehee.challengehub.service;

import daehee.challengehub.model.VerificationNotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// TODO: 에러 처리 관련된 내용은 Exception Handler를 통해서 api 모듈과 함께 처리 예정
@Service
public class VerificationNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String VERIFICATION_TOPIC = "verification-notifications";

    @Autowired
    public VerificationNotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 챌린지 인증 업로드 알림
    public boolean notifyCertificationUpload(String username, String challengeName) {
        try {
            String notification = username + "님이 챌린지 " + challengeName + "에 새로운 인증 사진을 업로드했습니다. 확인이 필요합니다.";
            kafkaTemplate.send(VERIFICATION_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    // 챌린지 인증 수정 알림
    public boolean notifyCertificationUpdate(String username, String challengeName) {
        try {
            String notification = username + "님이 제출한 인증 내용이 수정되었습니다. 챌린지 " + challengeName + " 인증을 다시 확인해주세요.";
            kafkaTemplate.send(VERIFICATION_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    // 챌린지 인증 삭제 알림
    public boolean notifyCertificationDeletion(String username, String challengeName) {
        try {
            String notification = username + "님이 제출한 챌린지 " + challengeName + " 인증이 삭제되었습니다. 관련 내용을 확인해주세요.";
            kafkaTemplate.send(VERIFICATION_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    public boolean processVerificationNotification(VerificationNotificationMessage message) {
        return switch (message.getType()) {
            case "추가" -> notifyCertificationUpload(message.getUserName(), message.getChallengeName());
            case "수정" -> notifyCertificationUpdate(message.getUserName(), message.getChallengeName());
            case "삭제" -> notifyCertificationDeletion(message.getUserName(), message.getChallengeName());
            default -> throw new IllegalArgumentException("알 수 없는 인증 알림 유형: " + message.getType());
        };
    }
}
