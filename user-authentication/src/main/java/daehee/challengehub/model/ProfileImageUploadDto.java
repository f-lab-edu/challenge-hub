package daehee.challengehub.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class ProfileImageUploadDto {
    private final MultipartFile imageFile;
    // TODO: 수정 예정, 추가적인 이미지 관련 필드 (예: 이미지 설명, 태그 등)
    private final String imageDescription;
    private final String imageTags;
}
