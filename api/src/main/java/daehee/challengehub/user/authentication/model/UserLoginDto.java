package daehee.challengehub.user.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserLoginDto {
    private final String email;
    private final String password;
    private final boolean rememberMe;
}
