import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daehee.challengehub.SocialApplication;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SocialApplication.class)
@AutoConfigureMockMvc
public class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCommunityFeed() throws Exception {
        MvcResult result = mockMvc.perform(get("/community/feed"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertNotNull(response.get("communityFeed"));
        assertTrue(((List<?>) response.get("communityFeed")).size() > 0);
    }

    @Test
    public void testCreateCommunityPost() throws Exception {
        String postJson = "{\"authorId\":101,\"postContent\":\"새로운 포스트 내용\",\"postTitle\":\"새로운 포스트\"}";
        MvcResult result = mockMvc.perform(post("/community/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("새로운 포스트", ((Map<?, ?>)response.get("createdPost")).get("postTitle"));
    }

    @Test
    public void testUpdateCommunityPost() throws Exception {
        String updatedPostJson = "{\"authorId\":101,\"postContent\":\"수정된 포스트 내용\",\"postTitle\":\"수정된 포스트\"}";
        MvcResult result = mockMvc.perform(put("/community/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPostJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("수정된 포스트", ((Map<?, ?>)response.get("updatedPost")).get("postTitle"));
    }

    @Test
    public void testDeleteCommunityPost() throws Exception {
        MvcResult result = mockMvc.perform(delete("/community/posts/3"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("포스트 삭제 성공: 포스트 ID 3", response.get("message"));
    }

    @Test
    public void testGetCommunityPosts() throws Exception {
        MvcResult result = mockMvc.perform(get("/community/posts"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertNotNull(response.get("posts"));
        assertTrue(((List<?>) response.get("posts")).size() > 0);
    }

    @Test
    public void testLikeCommunityPost() throws Exception {
        MvcResult result = mockMvc.perform(post("/community/posts/1/like"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals(String.format("포스트 ID %d에 좋아요 성공", 1), response.get("message"));
        assertNotNull(response.get("likedPost"));
    }
}
