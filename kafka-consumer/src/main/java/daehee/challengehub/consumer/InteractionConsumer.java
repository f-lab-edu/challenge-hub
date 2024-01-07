package daehee.challengehub.consumer;

import daehee.challengehub.model.InteractionNotificationMessage;
import daehee.challengehub.response.NotificationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import daehee.challengehub.service.InteractionNotificationService;

// TODO: 에러 처리 관련된 내용은 Exception Handler를 통해서 api 모듈과 함께 처리 예정
@Component
public class InteractionConsumer {

    private final InteractionNotificationService notificationService;
    private final ModelMapper modelMapper;

    @Autowired
    public InteractionConsumer(InteractionNotificationService notificationService, ModelMapper modelMapper) {
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
    }

    @KafkaListener(topics = "interaction-notifications", groupId = "interaction-group")
    public NotificationResponse consume(String jsonMessage) {
        try {
            InteractionNotificationMessage message = modelMapper.map(jsonMessage, InteractionNotificationMessage.class);
            boolean processResult = notificationService.processInteractionNotification(message);

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
