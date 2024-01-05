package daehee.challengehub.challenge.payment.repository;

import daehee.challengehub.challenge.payment.entity.Transaction;
import daehee.challengehub.challenge.payment.model.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class PaymentRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PaymentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Transaction saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .userId(transactionDto.getUserId())
                .transactionDate(Instant.now()) // TODO: 결제내역을 서버 시간으로 해도 되는건가?, 알아보고 수정 예정
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .status(transactionDto.getStatus())
                .build();

        return mongoTemplate.save(transaction, "transactions");
    }

    // 특정 사용자의 모든 거래 내역 조회
    public List<Transaction> findTransactionsByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Transaction.class, "transactions");
    }

    // 거래 내역 상세 조회
    public Transaction findTransactionById(String transactionId) {
        return mongoTemplate.findById(transactionId, Transaction.class, "transactions");
    }

    // 거래 유형 (입금/출금)에 따른 거래 내역 조회
    public List<Transaction> findTransactionsByType(String userId, String type) {
        Query query = new Query(Criteria.where("userId").is(userId).and("type").is(type));
        return mongoTemplate.find(query, Transaction.class, "transactions");
    }

    // 거래 상태 (완료/실패 등)에 따른 거래 내역 조회
    public List<Transaction> findTransactionsByStatus(String userId, String status) {
        Query query = new Query(Criteria.where("userId").is(userId).and("status").is(status));
        return mongoTemplate.find(query, Transaction.class, "transactions");
    }
}
