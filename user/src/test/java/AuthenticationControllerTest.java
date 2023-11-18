import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daehee.challengehub.UserApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSignup() throws Exception {
        String signupJson = "{\"username\":\"testUser\",\"email\":\"test@example.com\",\"password\":\"password123\"}";
        MvcResult result = mockMvc.perform(post("/auth/users")
                        .contentType("application/json;charset=UTF-8")
                        .content(signupJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("회원가입 성공", response.get("message"));
        assertNotNull(response.get("user"));
    }

    @Test
    public void testVerifyEmail() throws Exception {
        MvcResult result = mockMvc.perform(get("/auth/users/verify/validToken123"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("이메일 인증 성공", response.get("message"));
        assertEquals("validToken123", response.get("token"));
    }

    @Test
    public void testLogin() throws Exception {
        String loginJson = "{\"email\":\"user@example.com\",\"password\":\"password123\"}";
        MvcResult result = mockMvc.perform(post("/auth/login")
                        .contentType("application/json;charset=UTF-8")
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("로그인 성공", response.get("message"));
        assertEquals("user@example.com", response.get("userEmail"));
    }

    @Test
    public void testResetPassword() throws Exception {
        String resetPasswordJson = "{\"currentPassword\":\"currentPassword123\",\"newPassword\":\"newPassword456\"}";
        MvcResult result = mockMvc.perform(post("/auth/password/reset")
                        .contentType("application/json;charset=UTF-8")
                        .content(resetPasswordJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("비밀번호 재설정 성공", response.get("message"));
        assertEquals("newPassword456", response.get("newPassword"));
    }
}
