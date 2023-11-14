package daehee.challengehub.profile.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class ProfileImageUploadDto {
    private final MultipartFile imageFile;
}
