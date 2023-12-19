package daehee.challengehub.user.message.model;

import lombok.Data;

@Data
public class SendMessageResponseDto {
    private final String status;
    private final MessageDto message;
}
