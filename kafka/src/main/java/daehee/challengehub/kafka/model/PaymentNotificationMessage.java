package daehee.challengehub.kafka.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentNotificationMessage extends NotificationMessage {
    private double transactionAmount;
    private String transactionType;
}
