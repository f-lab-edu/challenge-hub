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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSendMessage() throws Exception {
        String messageJson = new JsonBuilder()
                .add("messageContent", "안녕하세요. 만나서 반갑습니다.")
                .build();
        MvcResult result = mockMvc.perform(post("/message/user/456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(messageJson))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("메시지 전송 성공", response.get("message"));
        assertNotNull(response.get("sentMessage"));
    }

    @Test
    public void testGetMessageHistory() throws Exception {
        MvcResult result = mockMvc.perform(get("/message/user/456/messages"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("메시지 목록 조회 성공", response.get("message"));
        assertNotNull(response.get("messages"));
        assertFalse(((List<?>) response.get("messages")).isEmpty());
    }

    @Test
    public void testGetChatRooms() throws Exception {
        MvcResult result = mockMvc.perform(get("/message/user/rooms"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        assertEquals("채팅방 목록 조회 성공", response.get("message"));
        assertNotNull(response.get("chatRooms"));
        assertFalse(((List<?>) response.get("chatRooms")).isEmpty());
    }
}
