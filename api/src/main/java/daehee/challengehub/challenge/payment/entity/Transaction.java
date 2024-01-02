package daehee.challengehub.challenge.payment.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Document
public class Transaction {
    @Id
    private String id;
    private String userId; // 사용자 ID
    private Instant transactionDate; // 거래 일시
    private double amount; // 금액
    private String type; // 거래 유형 (예: "deposit", "withdraw")
    private String status; // 거래 상태 (예: "completed", "failed")
}
