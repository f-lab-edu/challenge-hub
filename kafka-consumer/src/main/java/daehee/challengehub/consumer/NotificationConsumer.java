package daehee.challengehub.consumer;

import daehee.challengehub.interfaces.KafkaProducerService;
import daehee.challengehub.model.InteractionNotificationMessage;
import daehee.challengehub.model.ManagementNotificationMessage;
import daehee.challengehub.model.PaymentNotificationMessage;
import daehee.challengehub.model.VerificationNotificationMessage;
import daehee.challengehub.topic.KafkaTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final KafkaProducerService kafkaProducerService;
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationConsumer(KafkaProducerService kafkaProducerService, ModelMapper modelMapper) {
        this.kafkaProducerService = kafkaProducerService;
        this.modelMapper = modelMapper;
    }

    @KafkaListener(topics = KafkaTopic.INTERACTION, groupId = "interaction-group")
    public void consumeInteraction(String jsonMessage) {
        InteractionNotificationMessage message = modelMapper.map(jsonMessage, InteractionNotificationMessage.class);
        // 여기에 인터랙션 메시지 처리 로직을 구현
    }

    @KafkaListener(topics = KafkaTopic.MANAGEMENT, groupId = "management-group")
    public void consumeManagement(String jsonMessage) {
        ManagementNotificationMessage message = modelMapper.map(jsonMessage, ManagementNotificationMessage.class);
        // 여기에 관리 메시지 처리 로직을 구현
    }

    @KafkaListener(topics = KafkaTopic.PAYMENT, groupId = "payment-group")
    public void consumePayment(String jsonMessage) {
        PaymentNotificationMessage message = modelMapper.map(jsonMessage, PaymentNotificationMessage.class);
        // 여기에 결제 메시지 처리 로직을 구현
    }

    @KafkaListener(topics = KafkaTopic.VERIFICATION, groupId = "verification-group")
    public void consumeVerification(String jsonMessage) {
        VerificationNotificationMessage message = modelMapper.map(jsonMessage, VerificationNotificationMessage.class);
        // 여기에 인증 관련 메시지 처리 로직을 작성
    }
}

