package daehee.challengehub.user.message.model;

import daehee.challengehub.user.message.model.MessageDto;
import lombok.Data;

@Data
public class SendMessageResponseDto {
    private final String status;
    private final MessageDto message;
}
