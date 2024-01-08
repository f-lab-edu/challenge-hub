package daehee.challengehub.consumer;

import daehee.challengehub.model.VerificationNotificationMessage;
import daehee.challengehub.response.NotificationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import daehee.challengehub.service.VerificationNotificationService;

// TODO: 에러 처리 관련된 내용은 Exception Handler를 통해서 api 모듈과 함께 처리 예정
@Component
public class VerificationConsumer {

    private final VerificationNotificationService notificationService;
    private final ModelMapper modelMapper;

    @Autowired
    public VerificationConsumer(VerificationNotificationService notificationService, ModelMapper modelMapper) {
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
    }

    @KafkaListener(topics = "verification-notifications", groupId = "verification-group")
    public NotificationResponse consume(String jsonMessage) {
        try {
            VerificationNotificationMessage message = modelMapper.map(jsonMessage, VerificationNotificationMessage.class);
            boolean processResult = notificationService.processVerificationNotification(message);

            if (processResult) {
                return NotificationResponse.builder()
                        .success(true)
                        .message("알림이 성공적으로 처리되었습니다.")
                        .build();
            } else {
                return NotificationResponse.builder()
                        .success(false)
                        .message("알림 처리 중 일부 작업에 실패했습니다.")
                        .build();
            }
        } catch (Exception e) {
            return NotificationResponse.builder()
                    .success(false)
                    .message("알림 처리 중 오류 발생: " + e.getMessage())
                    .build();
        }
    }
}
