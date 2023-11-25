package daehee.challengehub.profile.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordChangeDto {
    private final String currentPassword;
    private final String newPassword;
}
