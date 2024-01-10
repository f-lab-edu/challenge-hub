package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class VerificationNotificationMessage {
    @NonNull private String userId;
    @NonNull private String userName;
    @NonNull private String challengeId;
    @NonNull private String challengeName;
    @NonNull private String type; // "추가", "수정", "삭제" 등의 알림 유형
}
