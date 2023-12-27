package daehee.challengehub.challenge.payment.model;

import java.time.Instant;

public class TransactionDTO {
    private String id;
    private String userId;
    private Instant transactionDate;
    private double amount;
    private String type;
    private String status;
}
