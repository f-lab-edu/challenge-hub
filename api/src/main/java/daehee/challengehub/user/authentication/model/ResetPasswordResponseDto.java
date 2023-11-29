package daehee.challengehub.user.authentication.model;

import lombok.Data;

@Data
public class ResetPasswordResponseDto {
    private final String message;
    private final String newPassword;
}
