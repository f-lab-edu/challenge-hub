package daehee.challengehub.challenge.payment.controller;

import daehee.challengehub.challenge.payment.entity.Transaction;
import daehee.challengehub.challenge.payment.model.TransactionDto;
import daehee.challengehub.challenge.payment.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentController(PaymentService paymentService, ModelMapper modelMapper) {
        this.paymentService = paymentService;
        this.modelMapper = modelMapper;
    }

    // 예치금 충전 및 인출 통합 처리
    // TODO: withdraw, deposit 관련된 부분을 굳이 따로 받을 필요가 있나? 그냥 Post에 전부 받아서 처리하면 될 거 같은데?
    @PostMapping
    public TransactionDto processTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction savedTransaction = paymentService.processTransaction(transactionDto);
        return modelMapper.map(savedTransaction, TransactionDto.class);
    }


    // 특정 사용자의 거래 내역 조회
    @GetMapping("/transaction")
    public List<TransactionDto> getTransactionsByUserId(@RequestParam String userId) {
        List<Transaction> transactions = paymentService.findTransactionsByUserId(userId);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .toList();
    }

    // 거래 내역 상세 조회
    @GetMapping("/transaction/{transactionId}")
    public TransactionDto getTransactionById(@PathVariable String transactionId) {
        Transaction transaction = paymentService.findTransactionById(transactionId);
        return modelMapper.map(transaction, TransactionDto.class);
    }

    // 거래 유형에 따른 거래 내역 조회
    @GetMapping("/transaction/type")
    public List<TransactionDto> getTransactionsByType(@RequestParam String userId, @RequestParam String type) {
        List<Transaction> transactions = paymentService.findTransactionsByType(userId, type);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .toList();
    }

    // 거래 상태에 따른 거래 내역 조회
    @GetMapping("/transaction/status")
    public List<TransactionDto> getTransactionsByStatus(@RequestParam String userId, @RequestParam String status) {
        List<Transaction> transactions = paymentService.findTransactionsByStatus(userId, status);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .toList();
    }
}
