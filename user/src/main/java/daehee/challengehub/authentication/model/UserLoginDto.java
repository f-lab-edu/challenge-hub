package daehee.challengehub.authentication.model;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class UserLoginDto {
    private final String email;
    private final String password;
    private final boolean rememberMe;
}
