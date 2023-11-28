package daehee.challengehub.user.message.model;

import daehee.challengehub.user.message.model.ChatRoomDto;
import lombok.Data;

import java.util.List;

@Data
public class ChatRoomsResponseDto {
    private final List<ChatRoomDto> chatRooms;
}
