package daehee.challengehub.user.authentication.model;

import lombok.Data;

@Data
public class SignupResponseDto {
    private final String message;
    private final UserSignupDto user;
}
