import daehee.challengehub.UserApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProfile() throws Exception {
        mockMvc.perform(get("/profile/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("sampleUser")))
                .andExpect(jsonPath("$.email", is("user@example.com")));
    }

    @Test
    public void testGetUserProfile() throws Exception {
        mockMvc.perform(get("/profile/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("sampleUser")))
                .andExpect(jsonPath("$.email", is("user@example.com")));
    }

    @Test
    public void testUpdateProfile() throws Exception {
        String profileJson = "{\"username\":\"updatedUser\",\"nickname\":\"UpdatedNickname\",\"email\":\"updated@example.com\",\"bio\":\"Updated bio\"}";
        mockMvc.perform(put("/profile/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("프로필 업데이트 성공: updatedUser")));
    }

    @Test
    public void testChangePassword() throws Exception {
        String passwordJson = "{\"currentPassword\":\"oldPassword\",\"newPassword\":\"newStrongPassword\"}";
        mockMvc.perform(put("/profile/user/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(passwordJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("비밀번호 변경 성공: newStrongPassword")));
    }

    @Test
    public void testGetAchievements() throws Exception {
        mockMvc.perform(get("/profile/user/achievements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].achievementDetails", is("10일 연속 챌린지 완료")))
                .andExpect(jsonPath("$[1].achievementDetails", is("커뮤니티에서 활발한 활동")));
    }
}
