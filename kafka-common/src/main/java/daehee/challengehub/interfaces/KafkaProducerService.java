package daehee.challengehub.interfaces;

import daehee.challengehub.response.NotificationResponse;

import java.util.concurrent.CompletableFuture;

public interface KafkaProducerService {
    CompletableFuture<NotificationResponse> sendMessage(String topic, String message);
}
