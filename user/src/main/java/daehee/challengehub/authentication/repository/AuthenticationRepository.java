package daehee.challengehub.authentication.repository;

import daehee.challengehub.authentication.model.UserSignupDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthenticationRepository {
    private final Map<String, UserSignupDto> users = new HashMap<>();

    // 사용자 정보 저장
    public void save(UserSignupDto user) {
        users.put(user.getEmail(), user);
    }

    // 이메일을 기반으로 사용자 정보 조회
    public UserSignupDto findByEmail(String email) {
        return users.get(email);
    }

    // 토큰을 기반으로 이메일 인증 상태 조회 (예시)
    public boolean isEmailVerified(String token) {
        // 여기에 토큰을 기반으로 이메일 인증 상태를 확인하는 로직 구현
        // 예를 들어, 토큰과 연관된 사용자의 인증 상태를 반환
        return true;
    }

    // 로그인 정보 확인 (예시)
    public boolean validateLogin(String email, String password) {
        UserSignupDto user = findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // 비밀번호 재설정 관련 로직 (예시)
    public void updatePassword(String email, String newPassword) {
        UserSignupDto user = users.get(email);
        if (user != null) {
            UserSignupDto updatedUser = UserSignupDto.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(newPassword)
                    .nickname(user.getNickname())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
            save(updatedUser);
        }
    }
}
