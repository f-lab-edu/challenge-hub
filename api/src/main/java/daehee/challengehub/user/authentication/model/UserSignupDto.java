package daehee.challengehub.user.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import daehee.challengehub.user.authentication.entity.User;

@Document(collection = "users")
@Getter
@Builder
@AllArgsConstructor
public class UserSignupDto {
    private final String username;
    private final String email;
    private final String password;
    private final String nickname;
    private final String phoneNumber;

    public User toEntity() {
        return new User(username, email, password, nickname, phoneNumber);
    }
}
