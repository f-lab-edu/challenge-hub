package daehee.challengehub.user.message.model;

import lombok.Data;

import java.util.List;

@Data
public class MessageHistoryResponseDto {
    private final List<MessageDto> messages;
}
