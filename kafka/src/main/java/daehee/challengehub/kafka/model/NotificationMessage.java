package daehee.challengehub.kafka.model;


import java.util.List;

public abstract class NotificationMessage {
    private List<String> userIds;
    private String message;
}
