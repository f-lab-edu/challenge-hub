package daehee.challengehub.management.service;

import org.springframework.stereotype.Service;

import daehee.challengehub.management.model.ChallengeDto;
import daehee.challengehub.management.model.ChallengeImageDto;
import daehee.challengehub.management.model.ChallengeTagDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagementService {

    // 챌린지 생성 로직
    public Map<String, Object> createChallenge(ChallengeDto challengeData) {
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 생성 성공");
        response.put("createdChallenge", newChallenge);
        return response;
    }

    // 챌린지 목록 조회 로직
    public Map<String, Object> getAllChallenges() {
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 목록 조회 성공");
        response.put("challenges", challenges);
        return response;
    }

    // 특정 챌린지 상세 조회 로직
    public Map<String, Object> getChallengeById(Long id) {
        ChallengeDto challenge = ChallengeDto.builder()
                .challengeId(1L)
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 상세 조회 성공");
        response.put("challenge", challenge);
        return response;
    }

    // 챌린지 수정 로직
    public Map<String, Object> updateChallenge(Long id, ChallengeDto challengeData) {
        ChallengeDto updatedChallenge = ChallengeDto.builder()
                .challengeId(1L)
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "챌린지 수정 성공");
        response.put("updatedChallenge", updatedChallenge);
        return response;
    }

    // 챌린지 삭제 로직
    public Map<String, String> deleteChallenge(Long id) {
        id = 1L;
        Map<String, String> response = new HashMap<>();
        response.put("message", "챌린지 삭제 성공: 챌린지 ID " + id);
        return response;
    }

    // 챌린지 참여 로직
    public Map<String, String> participateInChallenge(Long id) {
        id = 1L;
        Map<String, String> response = new HashMap<>();
        response.put("message", "챌린지 참여 성공: 챌린지 ID " + id);
        return response;
    }

    // 챌린지 태그 추가 로직
    public Map<String, Object> addTagToChallenge(Long id, ChallengeTagDto tagData) {
        ChallengeTagDto newTag = ChallengeTagDto.builder()
                .tagId(1L) // 임의의 태그 ID
                .tagName("새로운 태그")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "태그 추가 성공");
        response.put("tagDetails", tagData); // tagData는 요청에서 받은 태그 정보
        return response;
    }

    // 챌린지 태그 제거 로직
    public Map<String, String> removeTagFromChallenge(Long id, Long tagId) {
        id = 1L;
        tagId = 2L;
        Map<String, String> response = new HashMap<>();
        response.put("message", "태그 삭제 성공: 태그 ID " + tagId + " 챌린지 ID " + id);
        return response;
    }

    // 챌린지 이미지 업로드 로직
    public Map<String, Object> uploadImageToChallenge(Long id, ChallengeImageDto imageData) {
        ChallengeImageDto newImage = ChallengeImageDto.builder()
                .imageId(1L) // 임의의 이미지 ID
                .imageUrl("https://example.com/new_image.jpg")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "이미지 업로드 성공");
        response.put("imageDetails", imageData); // imageData는 요청에서 받은 이미지 정보
        return response;
    }

    // 챌린지 이미지 삭제 로직
    public Map<String, String> removeImageFromChallenge(Long id, Long imageId) {
        id = 1L;
        imageId = 2L;
        Map<String, String> response = new HashMap<>();
        response.put("message", "이미지 삭제 성공: 이미지 ID " + imageId + " 챌린지 ID " + id);
        return response;
    }
}
