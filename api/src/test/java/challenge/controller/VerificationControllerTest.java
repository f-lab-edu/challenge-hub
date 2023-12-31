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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class VerificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 챌린지 인증 업로드 테스트
    @Test
    public void testUploadVerification() throws Exception {
        String verificationJson = new JsonBuilder()
                .add("verificationText", "이번 주 도전 성공!")
                .build();
        MvcResult result = mockMvc.perform(post("/challenges/3/verification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(verificationJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("이번 주 도전 성공!", ((Map<?, ?>) response.get("newVerification")).get("verificationText"));
    }

    // 챌린지 인증 내역 조회 테스트
    @Test
    public void testGetVerifications() throws Exception {
        MvcResult result = mockMvc.perform(get("/challenges/3/verification"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals(2, ((List<?>) response.get("verifications")).size());
    }

    // 챌린지 인증 수정 테스트
    @Test
    public void testUpdateVerification() throws Exception {
        String updatedVerificationJson = new JsonBuilder()
                .add("verificationText", "챌린지 인증 수정됨")
                .build();
        MvcResult result = mockMvc.perform(put("/challenges/3/verification/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedVerificationJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("챌린지 인증 수정됨", ((Map<?, ?>) response.get("updatedVerification")).get("verificationText"));
    }

    // 챌린지 인증 삭제 테스트
    @Test
    public void testDeleteVerification() throws Exception {
        MvcResult result = mockMvc.perform(delete("/challenges/3/verification/1"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, String> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertTrue(response.get("message").contains("챌린지 인증 삭제 성공: 인증 ID 1"));
    }
}
