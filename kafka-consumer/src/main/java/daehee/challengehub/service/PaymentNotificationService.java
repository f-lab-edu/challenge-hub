package daehee.challengehub.service;

import daehee.challengehub.model.PaymentNotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// TODO: 에러 처리 관련된 내용은 Exception Handler를 통해서 api 모듈과 함께 처리 예정
@Service
public class PaymentNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String PAYMENT_TOPIC = "payment-notifications";

    @Autowired
    public PaymentNotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 예치금 충전 및 인출 알림
    public boolean notifyTransaction(String username, Double transactionAmount, String transactionType) {
        try {
            String transactionAction = transactionType.equals("deposit") ? "충전" : "인출";
            String notification = String.format("%s님의 계정에서 거래가 성공적으로 처리되었습니다. 거래 내용: %.2f %s",
                    username, transactionAmount, transactionAction);
            kafkaTemplate.send(PAYMENT_TOPIC, notification);
            return true;
        } catch (Exception e) {
            // 로깅 또는 예외 처리
            return false;
        }
    }

    public boolean processPaymentNotification(PaymentNotificationMessage message) {
        return notifyTransaction(message.getUserNames(), message.getTransactionAmount(), message.getTransactionType());
    }
}
