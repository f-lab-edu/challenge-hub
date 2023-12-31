package daehee.challengehub.challenge.interaction.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ChatRoom {
    @Id
    private String id;
    private String challengeId;
    private List<String> participants; // 참여자 ID의 리스트
}
