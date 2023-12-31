package daehee.challengehub.user.authentication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Field("userId") // MongoDB의 _id 필드와 매핑
    private String id;

    @Field("username")
    private String username;

    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("nickname")
    private String nickname;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("bio")
    private String bio;

    @Field("profile")
    private Profile profile;

    public User(String username, String email, String password, String nickname, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public static class Profile {
        @Field("profileImage")
        private String profileImage;

        @Field("joinedDate")
        private Instant joinedDate;
    }
}
