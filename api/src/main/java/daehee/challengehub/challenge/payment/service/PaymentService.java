package daehee.challengehub.challenge.payment.service;

import daehee.challengehub.challenge.payment.entity.Transaction;
import daehee.challengehub.challenge.payment.model.TransactionDto;
import daehee.challengehub.challenge.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // 예치금 충전 및 인출 처리
    public Transaction processTransaction(TransactionDto transactionDto) {
        return paymentRepository.saveTransaction(transactionDto);
    }

    // 특정 사용자의 모든 거래 내역 조회
    public List<Transaction> findTransactionsByUserId(String userId) {
        return paymentRepository.findTransactionsByUserId(userId);
    }

    // 거래 내역 상세 조회
    public Transaction findTransactionById(String transactionId) {
        return paymentRepository.findTransactionById(transactionId);
    }

    // 거래 유형 (입금/출금)에 따른 거래 내역 조회
    public List<Transaction> findTransactionsByType(String userId, String type) {
        return paymentRepository.findTransactionsByType(userId, type);
    }

    // 거래 상태 (완료/실패 등)에 따른 거래 내역 조회
    public List<Transaction> findTransactionsByStatus(String userId, String status) {
        return paymentRepository.findTransactionsByStatus(userId, status);
    }
}
