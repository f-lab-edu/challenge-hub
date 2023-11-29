package daehee.challengehub.user.message.model;

import daehee.challengehub.user.message.model.MessageDto;
import lombok.Data;

import java.util.List;

@Data
public class MessageHistoryResponseDto {
    private final List<MessageDto> messages;
}
