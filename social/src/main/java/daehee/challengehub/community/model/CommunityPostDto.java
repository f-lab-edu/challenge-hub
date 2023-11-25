package daehee.challengehub.community.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommunityPostDto {
    private final Long postId; // 게시물 ID
    private final Long authorId; // 작성자 ID
    private final String postContent; // 게시물 내용
    private final String postTitle; // 게시물 제목
    private final String creationDate; // 게시 날짜
    private final String lastEdited; // 마지막 수정 날짜
    private final int likeCount; // 좋아요 수
    private final int commentCount; // 댓글 수
}
