package daehee.challengehub.profile.controller;

import daehee.challengehub.profile.model.PasswordChangeDto;
import daehee.challengehub.profile.model.UserProfileDto;
import daehee.challengehub.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public Map<String, Object> getProfile(@PathVariable Long userId) {
        return profileService.getProfile(userId);
    }

    @PutMapping
    public Map<String, String> updateProfile(@RequestBody UserProfileDto userProfileDto) {
        return profileService.updateProfile(userProfileDto);
    }

    @PutMapping("/password")
    public Map<String, String> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        return profileService.changePassword(passwordChangeDto);
    }

    // TODO: 여기는 어떻게 구현해야할 지 다시 생각해보기 -> 데이터베이스에 이미지 URL을 넣어서 가져올 수도 있고 아예 파일 업로드를 해도 되는데, 전자가 나으려나?
    @PostMapping("/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        return profileService.uploadAvatar(file);
    }

    @GetMapping("/achievements")
    public Map<String, Object> getAchievements() {
        return profileService.getAchievements();
    }
}
