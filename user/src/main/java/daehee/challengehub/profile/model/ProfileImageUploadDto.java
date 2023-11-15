package daehee.challengehub.profile.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class ProfileImageUploadDto {
    private final MultipartFile imageFile; // TODO: 이것도 그냥 ImageUrl로 변경 시킬까 고민되는데 더 알아보고 바꿔보자
}
