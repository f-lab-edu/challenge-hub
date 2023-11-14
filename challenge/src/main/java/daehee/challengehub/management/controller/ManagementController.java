package daehee.challengehub.management.controller;

import daehee.challengehub.management.model.ChallengeDto;
import daehee.challengehub.management.model.ChallengeImageDto;
import daehee.challengehub.management.model.ChallengeTagDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ManagementController {
    // 챌린지 생성
    @PostMapping
    public ResponseEntity<ChallengeDto> createChallenge(@RequestBody ChallengeDto challengeData) {
        ChallengeDto newChallenge = ChallengeDto.builder()
                .challengeId(1L)
                .title("새로운 챌린지")
                .description("임의의 챌린지 설명")
                .tags(Arrays.asList("태그1", "태그2"))
                .imageUrls(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"))
                .startDate("2023-11-01")
                .endDate("2023-11-30")
                .createdBy("admin")
                .createdAt("2023-11-15")
                .lastModified("2023-11-15")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newChallenge);
    }

    // 챌린지 목록 조회
    @GetMapping
    public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
        List<ChallengeDto> challenges = Arrays.asList(
                ChallengeDto.builder()
                        .challengeId(1L)
                        .title("30일 요가 도전")
                        .description("초보자를 위한 매일 요가 도전.")
                        .tags(Arrays.asList("요가", "건강"))
                        .imageUrls(Arrays.asList("https://example.com/image1.jpg"))
                        .startDate("2023-11-01")
                        .endDate("2023-11-30")
                        .createdBy("사용자1")
                        .createdAt("2023-10-15")
                        .lastModified("2023-11-01")
                        .build(),
                ChallengeDto.builder()
                        .challengeId(2L)
                        .title("매일 명상하기")
                        .description("매일 15분간 명상하기.")
                        .tags(Arrays.asList("명상", "마음챙김"))
                        .imageUrls(Arrays.asList("https://example.com/image2.jpg"))
                        .startDate("2023-11-05")
                        .endDate("2023-12-05")
                        .createdBy("사용자2")
                        .createdAt("2023-10-20")
                        .lastModified("2023-11-04")
                        .build()
        );

        return ResponseEntity.ok(challenges);
    }

    // 특정 챌린지 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ChallengeDto> getChallengeById(@PathVariable Long id) {
        ChallengeDto challenge = ChallengeDto.builder()
                .challengeId(id)
                .title("30일 동안 매일 매일 런닝하기")
                .description("적어도 30분 동안 런닝하기")
                .tags(Arrays.asList("런닝", "운동"))
                .imageUrls(Arrays.asList("https://example.com/image3.jpg"))
                .startDate("2023-11-10")
                .endDate("2023-12-10")
                .createdBy("user3")
                .createdAt("2023-10-25")
                .lastModified("2023-11-09")
                .build();

        return ResponseEntity.ok(challenge);
    }

    // 챌린지 수정
    @PutMapping("/{id}")
    public ResponseEntity<ChallengeDto> updateChallenge(@PathVariable Long id, @RequestBody ChallengeDto challengeData) {
        ChallengeDto updatedChallenge = ChallengeDto.builder()
                .challengeId(id)
                .title("수정된 챌린지 제목")
                .description("수정된 챌린지 설명")
                .tags(Arrays.asList("수정된 태그1", "수정된 태그2"))
                .imageUrls(Arrays.asList("https://example.com/updated_image1.jpg", "https://example.com/updated_image2.jpg"))
                .startDate("2023-11-01")
                .endDate("2023-11-30")
                .createdBy("admin")
                .createdAt("2023-11-15")
                .lastModified("2023-11-15") // 가정된 수정 날짜
                .build();

        return ResponseEntity.ok(updatedChallenge);
    }

    // 챌린지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        return ResponseEntity.ok("챌린지 삭제 성공: 챌린지 ID " + id);
    }

    // 챌린지 참여
    @PostMapping("/{id}/participation")
    public ResponseEntity<String> participateInChallenge(@PathVariable Long id) {
        String responseMessage = "챌린지 참여 성공: 챌린지 ID " + id;
        return ResponseEntity.ok(responseMessage);
    }

    // 챌린지 태그 추가
    @PostMapping("/{id}/tags")
    public ResponseEntity<ChallengeTagDto> addTagToChallenge(@PathVariable Long id, @RequestBody ChallengeTagDto tagData) {
        ChallengeTagDto newTag = ChallengeTagDto.builder()
                .tagId(1L) // 임의의 태그 ID
                .tagName("새로운 태그")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newTag);
    }

    // 챌린지 태그 제거
    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<String> removeTagFromChallenge(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok("태그 삭제 성공: 태그 ID " + tagId + " 챌린지 ID " + id);
    }

    // 챌린지 이미지 업로드
    @PostMapping("/{id}/images")
    public ResponseEntity<ChallengeImageDto> uploadImageToChallenge(@PathVariable Long id, @RequestBody ChallengeImageDto imageData) {
        ChallengeImageDto newImage = ChallengeImageDto.builder()
                .imageId(1L) // 임의의 이미지 ID
                .imageUrl("https://example.com/new_image.jpg")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(newImage);
    }

    // 챌린지 이미지 삭제
    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseEntity<String> removeImageFromChallenge(@PathVariable Long id, @PathVariable Long imageId) {
        return ResponseEntity.ok("이미지 삭제 성공: 이미지 ID " + imageId + " 챌린지 ID " + id);
    }

    // 챌린지 규칙 설정, TODO: 구현을 해야하나
    @PostMapping("/{id}/rules")
    public ResponseEntity<String> setChallengeRules(@PathVariable Long id, @RequestBody String rules) {
        // 실제로는 규칙을 관리하는 로직이 필요합니다.
        return ResponseEntity.ok("챌린지 규칙 설정 완료: 챌린지 ID " + id);
    }

    // 챌린지 규칙 조회, TODO: 구현을 해야하나
    @GetMapping("/{id}/rules")
    public ResponseEntity<String> getChallengeRules(@PathVariable Long id) {
        // 실제로는 규칙을 조회하는 로직이 필요합니다.
        return ResponseEntity.ok("챌린지 규칙: 챌린지 ID " + id + "의 규칙 내용");
    }
}

