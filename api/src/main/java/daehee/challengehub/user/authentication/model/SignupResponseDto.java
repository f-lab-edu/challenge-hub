package daehee.challengehub.user.authentication.model;

import lombok.Data;
import daehee.challengehub.user.authentication.entity.User;

@Data
public class SignupResponseDto {
    private final String message;
    private final User user;
}
