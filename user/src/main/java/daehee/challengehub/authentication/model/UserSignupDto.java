package daehee.challengehub.authentication.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignupDto {
    private final String username;
    private final String email;
    private final String password;
    private final String nickname;
    private final String phoneNumber;
}
