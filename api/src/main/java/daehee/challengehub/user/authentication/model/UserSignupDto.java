package daehee.challengehub.user.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSignupDto {
    private final String username;
    private final String email;
    private final String password;
    private final String nickname;
    private final String phoneNumber;
}
