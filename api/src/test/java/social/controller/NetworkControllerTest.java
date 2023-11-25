package social.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daehee.challengehub.ApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class NetworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFollowUser() throws Exception {
        MvcResult result = mockMvc.perform(post("/network/follow/456"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals(String.format("사용자 %d 팔로우 성공. 상호 팔로우 상태: 예", 456), response.get("message"));
        assertNotNull(response.get("followDetails"));
    }

    @Test
    public void testGetFollowings() throws Exception {
        MvcResult result = mockMvc.perform(get("/network/following"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertNotNull(response.get("followingList"));
        assertFalse(((List<?>) response.get("followingList")).isEmpty());
    }

    @Test
    public void testUnfollowUser() throws Exception {
        MvcResult result = mockMvc.perform(delete("/network/follow/1"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("사용자 ID 1 언팔로우 성공", response.get("message"));
    }

    @Test
    public void testGetFollowers() throws Exception {
        MvcResult result = mockMvc.perform(get("/network/followers"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertNotNull(response.get("followers"));
        assertFalse(((List<?>) response.get("followers")).isEmpty());
    }
}
