package daehee.challengehub.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
@AllArgsConstructor
public class UserLoginDto {
    private final String email;
    private final String password;
    private final boolean rememberMe;
}
