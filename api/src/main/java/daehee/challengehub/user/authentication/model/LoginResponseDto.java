package daehee.challengehub.user.authentication.model;

import lombok.Data;

@Data
public class LoginResponseDto {
    private final String message;
    private final String userEmail;
}
