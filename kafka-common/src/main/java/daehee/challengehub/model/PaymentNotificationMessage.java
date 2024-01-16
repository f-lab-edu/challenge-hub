package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class PaymentNotificationMessage {
    @NonNull private String userId;
    @NonNull private String userName;
    @NonNull private Double transactionAmount;
    @NonNull private String transactionType;
}
