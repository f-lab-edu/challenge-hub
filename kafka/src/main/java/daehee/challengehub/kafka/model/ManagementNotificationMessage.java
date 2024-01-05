package daehee.challengehub.kafka.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ManagementNotificationMessage extends NotificationMessage {
    private String challengeName;
}
