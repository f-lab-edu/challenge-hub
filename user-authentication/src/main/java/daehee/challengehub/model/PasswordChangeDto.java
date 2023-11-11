package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordChangeDto {
    private final String currentPassword;
    private final String newPassword;
    // TODO: 수정 예정, 추가적인 보안 관련 필드 (예: 보안 질문, 보안 답변 등)
    private final String securityQuestion;
    private final String securityAnswer;
}
