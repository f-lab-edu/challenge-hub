package daehee.challengehub.kafka.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InteractionNotificationMessage extends NotificationMessage {
    private String chatMessage;
    private String challengeName;

}
