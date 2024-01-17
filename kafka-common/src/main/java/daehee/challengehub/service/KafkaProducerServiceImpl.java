package daehee.challengehub.service;

import daehee.challengehub.interfaces.KafkaProducerService;
import daehee.challengehub.response.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public CompletableFuture<NotificationResponse> sendMessage(String topic, String message) {
        CompletableFuture<SendResult<String, String>> future =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return kafkaTemplate.send(topic, message).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });

        return future
                .thenApply(result -> NotificationResponse.builder()
                        .success(true)
                        .message("메시지가 성공적으로 전송되었습니다")
                        .build())
                .exceptionally(ex -> NotificationResponse.builder()
                        .success(false)
                        .message("메시지 전송 실패: " + ex.getCause().getMessage())
                        .build());
    }
}
