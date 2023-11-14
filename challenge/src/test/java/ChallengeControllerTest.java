import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateChallenge() throws Exception {
        mockMvc.perform(post("/challenges"))
                .andExpect(status().isOk());
    }
}
