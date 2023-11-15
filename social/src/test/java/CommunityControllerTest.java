import daehee.challengehub.SocialApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = SocialApplication.class)
@AutoConfigureMockMvc
public class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCommunityFeed() throws Exception {
        mockMvc.perform(get("/community/feed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].postTitle", is("첫 번째 커뮤니티 포스트")))
                .andExpect(jsonPath("$[1].postTitle", is("두 번째 커뮤니티 포스트")));
    }

    @Test
    public void testCreateCommunityPost() throws Exception {
        String postJson = "{\"authorId\":101,\"postContent\":\"새로운 포스트 내용\",\"postTitle\":\"새로운 포스트\"}";
        mockMvc.perform(post("/community/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("커뮤니티 포스트 생성 성공: 새로운 포스트")));
    }

    @Test
    public void testUpdateCommunityPost() throws Exception {
        String updatedPostJson = "{\"authorId\":101,\"postContent\":\"수정된 포스트 내용\",\"postTitle\":\"수정된 포스트\"}";
        mockMvc.perform(put("/community/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPostJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postTitle", is("수정된 포스트")))
                .andExpect(jsonPath("$.postContent", is("수정된 포스트 내용")));
    }

    @Test
    public void testDeleteCommunityPost() throws Exception {
        mockMvc.perform(delete("/community/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("포스트 삭제 성공: 포스트 ID 1")));
    }

    @Test
    public void testGetCommunityPosts() throws Exception {
        mockMvc.perform(get("/community/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].postTitle", is("첫 번째 포스트")))
                .andExpect(jsonPath("$[1].postTitle", is("두 번째 포스트")));
    }

    @Test
    public void testLikeCommunityPost() throws Exception {
        mockMvc.perform(post("/community/posts/1/like"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("포스트 ID 1에 좋아요 성공")));
    }
}
