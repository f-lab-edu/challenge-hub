package daehee.challengehub.challenge.payment.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class TransactionDto {
    private String id;
    private String userId;
    private Instant transactionDate;
    private double amount;
    private String type;
    private String status;
}
