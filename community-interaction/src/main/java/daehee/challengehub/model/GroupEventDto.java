package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupEventDto {
    private final Long groupId; // 이벤트가 속한 그룹 ID
    private final String eventName; // 이벤트 이름
    private final String eventDescription; // 이벤트 설명
    private final String eventDate; // 이벤트 날짜
    private final String location; // 이벤트 장소
    private final Boolean isOnline; // 온라인 여부
}
