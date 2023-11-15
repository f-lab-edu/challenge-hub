import daehee.challengehub.UserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSendMessage() throws Exception {
        String messageJson = "{\"messageContent\":\"안녕하세요. 만나서 반갑습니다.\"}";
        mockMvc.perform(post("/message/user/456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(messageJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageContent", containsString("안녕하세요. 만나서 반갑습니다.")));
    }

    @Test
    public void testGetMessageHistory() throws Exception {
        mockMvc.perform(get("/message/user/456/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].messageContent", containsString("안녕하세요.")))
                .andExpect(jsonPath("$[1].messageContent", containsString("만나서 반갑습니다.")));
    }

    @Test
    public void testGetChatRooms() throws Exception {
        mockMvc.perform(get("/message/user/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Room 1")))
                .andExpect(content().string(containsString("Room 2")));
    }
}
