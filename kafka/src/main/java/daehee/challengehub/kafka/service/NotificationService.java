package daehee.challengehub.kafka.service;

import daehee.challengehub.kafka.model.InteractionNotificationMessage;
import daehee.challengehub.kafka.model.ManagementNotificationMessage;
import daehee.challengehub.kafka.model.NotificationMessage;
import daehee.challengehub.kafka.model.PaymentNotificationMessage;
import daehee.challengehub.kafka.model.VerificationNotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    private static final String INTERACTION_TOPIC = "interaction-notifications";
    private static final String MANAGEMENT_TOPIC = "management-notifications";
    private static final String VERIFICATION_TOPIC = "verification-notifications";
    private static final String PAYMENT_TOPIC = "payment-notifications";

    @Autowired
    public NotificationService(KafkaTemplate<String, NotificationMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationMessage message) {
        if (message instanceof InteractionNotificationMessage) {
            kafkaTemplate.send(INTERACTION_TOPIC, message);
        } else if (message instanceof ManagementNotificationMessage) {
            kafkaTemplate.send(MANAGEMENT_TOPIC, message);
        } else if (message instanceof VerificationNotificationMessage) {
            kafkaTemplate.send(VERIFICATION_TOPIC, message);
        } else if (message instanceof PaymentNotificationMessage) {
            kafkaTemplate.send(PAYMENT_TOPIC, message);
        }
    }
}
