import daehee.challengehub.UserApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSignup() throws Exception {
        String signupJson = "{\"username\":\"testUser\",\"email\":\"test@example.com\",\"password\":\"password123\"}";
        mockMvc.perform(post("/auth/users")
                        .contentType("application/json")
                        .content(signupJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("회원가입 성공")));
    }

    @Test
    public void testVerifyEmail() throws Exception {
        mockMvc.perform(get("/auth/users/verify/validToken123"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("이메일 인증 성공")));
    }

    @Test
    public void testSignupWithSocial() throws Exception {
        String socialSignupJson = "{\"provider\":\"Google\",\"token\":\"validToken\"}";
        mockMvc.perform(post("/auth/users/social")
                        .contentType("application/json")
                        .content(socialSignupJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("소셜 계정으로 회원가입 성공")));
    }

    @Test
    public void testLogin() throws Exception {
        String loginJson = "{\"email\":\"user@example.com\",\"password\":\"password123\"}";
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("로그인 성공")));
    }

    @Test
    public void testLoginWithSocial() throws Exception {
        String socialLoginJson = "{\"provider\":\"Facebook\",\"token\":\"sampleTokenFacebook123\"}";
        mockMvc.perform(post("/auth/login/social")
                        .contentType("application/json")
                        .content(socialLoginJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("소셜 로그인 성공")));
    }

    @Test
    public void testResetPassword() throws Exception {
        String resetPasswordJson = "{\"currentPassword\":\"currentPassword123\",\"newPassword\":\"newPassword456\"}";
        mockMvc.perform(post("/auth/password/reset")
                        .contentType("application/json")
                        .content(resetPasswordJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("비밀번호 재설정 성공")));
    }
}
