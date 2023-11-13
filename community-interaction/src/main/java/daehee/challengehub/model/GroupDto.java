package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupDto {
    private final Long groupId; // 그룹 ID
    private final String groupName; // 그룹 이름
    private final String groupDescription; // 그룹 설명
    private final Long creatorId; // 그룹 생성자의 사용자 ID
    private final String creationDate; // 그룹 생성 날짜
}
