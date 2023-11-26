package daehee.challengehub.user.message.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatRoomsResponseDto {
    private final List<ChatRoomDto> chatRooms;
}
