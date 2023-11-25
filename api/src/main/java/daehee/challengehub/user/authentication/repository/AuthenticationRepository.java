package daehee.challengehub.user.authentication.repository;

import daehee.challengehub.user.authentication.model.UserSignupDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthenticationRepository {
    private final Map<String, UserSignupDto> users = new HashMap<>();

    public AuthenticationRepository() {
        // 초기화 시 하드코딩된 사용자 데이터를 저장
        UserSignupDto standardUser = UserSignupDto.builder()
                .username("standardUser")
                .email("standard@example.com")
                .password("password123")
                .build();
        users.put(standardUser.getEmail(), standardUser);
    }

    // 사용자 정보 저장
    public void save(UserSignupDto user) {
        users.put(user.getEmail(), user);
    }

    // 이메일을 기반으로 사용자 정보 조회
    public UserSignupDto findByEmail(String email) {
        return users.get(email);
    }

    // 토큰을 기반으로 이메일 인증 상태 조회
    public boolean isEmailVerified(String token) {
        // 토큰 확인 로직 구현 (여기서는 단순 예시로 true 반환)
        return true;
    }

    // 로그인 정보 확인
    public boolean validateLogin(String email, String password) {
        UserSignupDto user = findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // 비밀번호 재설정 관련 로직
    public void updatePassword(String email, String newPassword) {
        UserSignupDto user = users.get(email);
        if (user != null) {
            UserSignupDto updatedUser = UserSignupDto.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(newPassword)
                    // 하드코딩된 데이터가 없는 경우, 기존 값 유지
                    .nickname(user.getNickname() != null ? user.getNickname() : "")
                    .phoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber() : "")
                    .build();
            save(updatedUser);
        }
    }
}
