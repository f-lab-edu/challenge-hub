package challenge.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daehee.challengehub.ApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import util.JsonBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class ManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 챌린지 생성 테스트
    @Test
    public void testCreateChallenge() throws Exception {
        String challengeJson = new JsonBuilder()
                .add("title", "새로운 챌린지")
                .add("description", "임의의 챌린지 설명")
                .build();
        MvcResult result = mockMvc.perform(post("/challenges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(challengeJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("새로운 챌린지", ((Map<?, ?>) response.get("createdChallenge")).get("title"));
    }

    // 챌린지 목록 조회 테스트
    @Test
    public void testGetAllChallenges() throws Exception {
        MvcResult result = mockMvc.perform(get("/challenges"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertFalse(((List<?>) response.get("challenges")).isEmpty());
    }

    // 특정 챌린지 상세 조회 테스트
    @Test
    public void testGetChallengeById() throws Exception {
        MvcResult result = mockMvc.perform(get("/challenges/1"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("30일 동안 매일 매일 런닝하기", ((Map<?, ?>) response.get("challenge")).get("title"));
    }

    // 챌린지 수정 테스트
    @Test
    public void testUpdateChallenge() throws Exception {
        String updatedChallengeJson = new JsonBuilder()
                .add("title", "수정된 챌린지 제목")
                .add("description", "수정된 챌린지 설명")
                .build();
        MvcResult result = mockMvc.perform(put("/challenges/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedChallengeJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("수정된 챌린지 제목", ((Map<?, ?>) response.get("updatedChallenge")).get("title"));
    }

    // 챌린지 삭제 테스트
    @Test
    public void testDeleteChallenge() throws Exception {
        MvcResult result = mockMvc.perform(delete("/challenges/1"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(response.get("message").contains("챌린지 삭제 성공"));
    }

    // 챌린지 참여 테스트
    @Test
    public void testParticipateInChallenge() throws Exception {
        MvcResult result = mockMvc.perform(post("/challenges/1/participation"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(response.get("message").contains("챌린지 참여 성공"));
    }

    // 챌린지 태그 추가 테스트
    @Test
    public void testAddTagToChallenge() throws Exception {
        String tagJson = new JsonBuilder()
                .add("tagName", "새로운 태그")
                .build();
        MvcResult result = mockMvc.perform(post("/challenges/1/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("새로운 태그", ((Map<?, ?>) response.get("tagDetails")).get("tagName"));
    }

    // 챌린지 태그 제거 테스트
    @Test
    public void testRemoveTagFromChallenge() throws Exception {
        MvcResult result = mockMvc.perform(delete("/challenges/1/tags/2"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(response.get("message").contains("태그 삭제 성공"));
    }

    // 챌린지 이미지 업로드 테스트
    @Test
    public void testUploadImageToChallenge() throws Exception {
        String imageJson = new JsonBuilder()
                .add("imageUrl", "https://example.com/new_image.jpg")
                .build();
        MvcResult result = mockMvc.perform(post("/challenges/1/images")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("https://example.com/new_image.jpg", ((Map<?, ?>) response.get("imageDetails")).get("imageUrl"));
    }

    // 챌린지 이미지 삭제 테스트
    @Test
    public void testRemoveImageFromChallenge() throws Exception {
        MvcResult result = mockMvc.perform(delete("/challenges/1/images/2"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(response.get("message").contains("이미지 삭제 성공"));
    }
}
