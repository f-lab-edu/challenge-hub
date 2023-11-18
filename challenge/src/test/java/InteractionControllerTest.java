import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daehee.challengehub.ChallengeApplication;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ChallengeApplication.class)
@AutoConfigureMockMvc
public class InteractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 챌린지에 대한 댓글 작성 테스트
    @Test
    public void testPostComment() throws Exception {
        String commentJson = "{\"commentText\":\"덕분에 즐겁게 챌린지 즐겼습니다. 감사합니다.\"}";
        MvcResult result = mockMvc.perform(post("/challenges/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("덕분에 즐겁게 챌린지 즐겼습니다. 감사합니다.", ((Map) response.get("comment")).get("commentText"));
    }

    // 챌린지 댓글 목록 조회 테스트
    @Test
    public void testGetComments() throws Exception {
        MvcResult result = mockMvc.perform(get("/challenges/1/comments"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(((List) response.get("comments")).size() == 2);
    }

    // 챌린지별 리더보드 조회 테스트
    @Test
    public void testGetLeaderboard() throws Exception {
        MvcResult result = mockMvc.perform(get("/challenges/1/leaderboard"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(((List) response.get("leaderboard")).size() == 2);
    }

    // 참여자 상세 정보 조회 테스트
    @Test
    public void testGetParticipantDetails() throws Exception {
        MvcResult result = mockMvc.perform(get("/challenges/1/participants/details"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(((List) response.get("participants")).size() == 2);
    }

    // 참여자 관리 테스트
    @Test
    public void testManageParticipants() throws Exception {
        String participantJson = "{\"participantUsername\":\"user1\"}";
        MvcResult result = mockMvc.perform(post("/challenges/1/participants/manage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(participantJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(response.get("message").contains("챌린지 ID 1에 대한 참여자 관리 성공"));
    }
}

