package daehee.challengehub.challenge.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionDto {
    private String transactionId;
    private String userId;
    private Instant transactionDate;
    private Double amount;
    private String type;
    private String status;
}
