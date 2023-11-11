package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignupDto {
    private final String username;
    private final String email;
    private final String password;
    // TODO: 수정 예정, 추가적인 필드 (예: 닉네임, 연락처 등)
    private final String nickname;
    private final String phoneNumber;
}
