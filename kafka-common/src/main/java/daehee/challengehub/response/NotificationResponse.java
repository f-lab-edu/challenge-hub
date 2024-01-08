package daehee.challengehub.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationResponse {
    private Boolean success;
    private String message;
}
