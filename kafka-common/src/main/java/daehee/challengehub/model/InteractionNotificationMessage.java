package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class InteractionNotificationMessage {
    @NonNull private List<String> userIds;
    @NonNull private List<String> userNames;
    @NonNull private String chatMessage;
    @NonNull private String challengeId;
    @NonNull private String challengeName;
}
