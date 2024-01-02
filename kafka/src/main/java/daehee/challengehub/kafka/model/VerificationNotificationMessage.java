package daehee.challengehub.kafka.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VerificationNotificationMessage extends NotificationMessage {
    private String verificationDetails;
    private String challengeName;
}
