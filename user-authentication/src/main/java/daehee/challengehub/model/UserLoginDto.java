package daehee.challengehub.model;

import lombok.Data;

@Data
public class UserLoginDto {
<<<<<<< HEAD
    private String email;
    private String password;

    // 추가적인 로그인 관련 필드 (예: 로그인 유지, 2단계 인증 등)
    private boolean rememberMe;
    private String twoFactorAuthCode;

    // 기본 생성자
    public UserLoginDto() {
    }

    // 모든 필드를 포함하는 생성자
    public UserLoginDto(String email, String password, boolean rememberMe, String twoFactorAuthCode) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
        this.twoFactorAuthCode = twoFactorAuthCode;
    }

    // Getter, Setter는 Lombok의 @Data 어노테이션으로 자동 생성됩니다.
=======
    private final String email;
    private final String password;
    private final boolean rememberMe;
>>>>>>> 915ae7e (DELETE : UserController에서 2단계 인증 기능 삭제)
}
