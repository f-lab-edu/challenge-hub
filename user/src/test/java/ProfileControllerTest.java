import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daehee.challengehub.UserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProfile() throws Exception {
        MvcResult result = mockMvc.perform(get("/profile/123"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("프로필 조회 성공", response.get("message"));
        assertNotNull(response.get("profile"));
        assertEquals("sampleUser", ((Map<?, ?>)response.get("profile")).get("username"));
    }

    @Test
    public void testUpdateProfile() throws Exception {
        String profileJson = new JsonBuilder()
                .add("username", "updatedUser")
                .add("nickname", "UpdatedNickname")
                .add("email", "updated@example.com")
                .add("bio", "Updated bio")
                .build();
        MvcResult result = mockMvc.perform(put("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("프로필 업데이트 성공", response.get("message"));
        assertEquals("updatedUser", response.get("updatedProfile"));
    }

    @Test
    public void testChangePassword() throws Exception {
        String passwordJson = new JsonBuilder()
                .add("currentPassword", "oldPassword")
                .add("newPassword", "newStrongPassword")
                .build();
        MvcResult result = mockMvc.perform(put("/profile/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(passwordJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("비밀번호 변경 성공", response.get("message"));
        assertEquals("newStrongPassword", response.get("newPassword"));
    }

    @Test
    public void testGetAchievements() throws Exception {
        MvcResult result = mockMvc.perform(get("/profile/achievements"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("성과 목록 조회 성공", response.get("message"));
        assertFalse(((List<?>) response.get("achievements")).isEmpty());
    }
}
