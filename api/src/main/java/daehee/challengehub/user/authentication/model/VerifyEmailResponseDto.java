package daehee.challengehub.user.authentication.model;

import lombok.Data;

@Data
public class VerifyEmailResponseDto {
    private final String message;
    private final String token;
}
