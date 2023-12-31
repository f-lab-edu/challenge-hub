package daehee.challengehub.user.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileImageUploadDto {
    private final String imageUrl; // 클라우드 스토리지에 업로드된 이미지의 URL
}
