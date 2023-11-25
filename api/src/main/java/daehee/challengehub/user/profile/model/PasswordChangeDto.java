package daehee.challengehub.user.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PasswordChangeDto {
    private final String currentPassword;
    private final String newPassword;
}
