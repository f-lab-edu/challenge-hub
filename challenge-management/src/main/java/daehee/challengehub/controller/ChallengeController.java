package daehee.challengehub.controller;

import daehee.challengehub.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ChallengeController {

    // 챌린지 생성
    @PostMapping
    public ResponseEntity<String> createChallenge(@RequestBody ChallengeDto challengeDto) {
        // 임의의 챌린지 생성 데이터
        ChallengeDto newChallenge = ChallengeDto.builder()
                .title("새로운 건강 챌린지")
                .description("매일 10,000보 걷기 챌린지")
                .startDate("2023-11-01")
                .endDate("2023-12-01")
                .category("건강")
                .difficulty("초급")
                .isPublic(true)
                .createdBy("User123")
                .build();

        String responseMessage = String.format("챌린지 생성 성공: %s", newChallenge.getTitle());
        return ResponseEntity.ok(responseMessage);
    }


    // 챌린지 목록 조회
    @GetMapping
    public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
        List<ChallengeDto> challenges = Arrays.asList(
                ChallengeDto.builder()
                        .title("10,000보 걷기 챌린지")
                        .description("매일 10,000보를 걷는 챌린지")
                        .startDate("2023-11-01")
                        .endDate("2023-11-30")
                        .category("건강")
                        .difficulty("초급")
                        .isPublic(true)
                        .createdBy("User123")
                        .build(),
                ChallengeDto.builder()
                        .title("책 읽기 챌린지")
                        .description("매주 새로운 책 읽기")
                        .startDate("2023-11-15")
                        .endDate("2023-12-15")
                        .category("교육")
                        .difficulty("중급")
                        .isPublic(false)
                        .createdBy("User456")
                        .build()
        );

        return ResponseEntity.ok(challenges);
    }


    // 특정 챌린지 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ChallengeDto> getChallengeById(@PathVariable Long id) {
        // 임의의 챌린지 상세 데이터 생성
        ChallengeDto challenge = ChallengeDto.builder()
                .title("도전! 일찍 일어나기")
                .description("매일 아침 6시에 기상하는 챌린지")
                .startDate("2023-10-01")
                .endDate("2023-10-31")
                .category("생활 습관")
                .difficulty("중급")
                .isPublic(true)
                .createdBy("User789")
                .build();

        return ResponseEntity.ok(challenge);
    }



    // 챌린지 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody ChallengeDto challengeDto) {
        // 임의의 챌린지 수정 데이터
        ChallengeDto updatedChallenge = ChallengeDto.builder()
                .title("수정된 챌린지 제목")
                .description("수정된 챌린지 설명")
                .startDate("2023-10-15")
                .endDate("2023-11-15")
                .category("건강")
                .difficulty("초급")
                .isPublic(false)
                .createdBy("User123")
                .build();

        String responseMessage = String.format("챌린지(ID: %d) 수정 성공: %s", id, updatedChallenge.getTitle());
        return ResponseEntity.ok(responseMessage);
    }



    // 챌린지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        // 임의의 챌린지 삭제 시나리오 (성공)
        String successMessage = String.format("챌린지(ID: %d) 삭제 성공", id);

        // 임의의 챌린지 삭제 실패 시나리오 (예: 존재하지 않는 ID)
        String failureMessage = String.format("챌린지(ID: %d) 삭제 실패: 존재하지 않는 챌린지", id);

        // 시나리오에 따라 성공 또는 실패 메시지 반환
        boolean isDeletionSuccessful = true; // 이 값을 변경하여 성공/실패 시나리오 선택
        return ResponseEntity.ok(isDeletionSuccessful ? successMessage : failureMessage);
    }

    // 챌린지 참여
    @PostMapping("/{id}/participation")
    public ResponseEntity<String> participateInChallenge(@PathVariable Long id, @RequestBody ChallengeParticipationDto challengeParticipationDto) {
        // 임의의 챌린지 참여 데이터 생성
        ChallengeParticipationDto newParticipation = ChallengeParticipationDto.builder()
                .userId(123L)
                .challengeId(id)
                .participationStatus("활성")
                .startDate("2023-11-15")
                .endDate("2023-12-15")
                .build();

        String responseMessage = String.format("사용자(ID: %d)가 챌린지(ID: %d)에 참여 성공: %s부터 %s까지",
                newParticipation.getUserId(), newParticipation.getChallengeId(), newParticipation.getStartDate(), newParticipation.getEndDate());
        return ResponseEntity.ok(responseMessage);
    }

    // 챌린지 진행 상태 조회
    @GetMapping("/{id}/progress")
    public ResponseEntity<ChallengeProgressReportDto> getChallengeProgress(@PathVariable Long id) {
        // 임의의 챌린지 진행 상태 데이터 생성
        ChallengeProgressReportDto progressReport = ChallengeProgressReportDto.builder()
                .challengeId(id)
                .progressDetails("챌린지 진행 중, 5일 중 3일 완료")
                .reportDate("2023-11-15")
                .progressPercentage(60.0)
                .build();

        return ResponseEntity.ok(progressReport);
    }

    // 챌린지 성과 등록
    @PostMapping("/{id}/feedback")
    public ResponseEntity<String> addChallengeFeedback(@PathVariable Long id, @RequestBody ChallengeFeedbackDto challengeFeedbackDto) {
        // 임의의 피드백 데이터 생성
        ChallengeFeedbackDto newFeedback = ChallengeFeedbackDto.builder()
                .userId(123L) // 사용자 ID
                .challengeId(id) // 챌린지 ID
                .feedback("챌린지는 매우 도전적이었습니다!") // 피드백 내용
                .createdAt("2023-11-11T10:00:00") // 피드백 작성 시간
                .build();

        String responseMessage = String.format("챌린지 ID %d에 대한 피드백 등록 성공: %s", id, newFeedback.getFeedback());
        return ResponseEntity.ok(responseMessage);
    }

    // 챌린지에 태그 추가
    @PostMapping("/{id}/tags")
    public ResponseEntity<String> addTagToChallenge(@PathVariable Long id, @RequestBody ChallengeTagDto challengeTagDto) {
        // 임의의 태그 데이터 생성
        ChallengeTagDto newTag = ChallengeTagDto.builder()
                .challengeId(id) // 챌린지 ID
                .tag("도전") // 태그 내용
                .tagDescription("새로운 도전을 시작하세요!") // 태그 설명
                .build();

        String responseMessage = String.format("챌린지 ID %d에 태그 '%s' 추가 성공: %s",
                id, newTag.getTag(), newTag.getTagDescription());
        return ResponseEntity.ok(responseMessage);
    }


    // 챌린지에서 태그 제거
    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<String> removeTagFromChallenge(@PathVariable Long id, @PathVariable Long tagId) {
        // 임의의 태그 제거 시나리오
        ChallengeTagDto removedTag = ChallengeTagDto.builder()
                .challengeId(id) // 챌린지 ID
                .tag("도전") // 제거될 태그 내용
                .tagDescription("이 태그는 더 이상 유효하지 않습니다.") // 태그 설명
                .build();

        String responseMessage = String.format("챌린지 ID %d에서 태그 ID %d ('%s') 제거 성공",
                id, tagId, removedTag.getTag());
        return ResponseEntity.ok(responseMessage);
    }


    // 챌린지에 이미지 업로드
    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadImageToChallenge(@PathVariable Long id, @RequestBody ChallengeImageDto challengeImageDto) {
        // 임의의 이미지 업로드 시나리오
        ChallengeImageDto newImage = ChallengeImageDto.builder()
                .challengeId(id) // 챌린지 ID
                .imageUrl("https://example.com/image.jpg") // 이미지 URL
                .description("새로운 챌린지 이미지 설명") // 이미지 설명
                .build();

        String responseMessage = String.format("챌린지 ID %d에 이미지 '%s' 업로드 성공",
                id, newImage.getImageUrl());
        return ResponseEntity.ok(responseMessage);
    }



    // 챌린지에서 이미지 제거
    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseEntity<String> removeImageFromChallenge(@PathVariable Long id, @PathVariable Long imageId) {
        // 임의의 이미지 제거 시나리오
        String responseMessage = String.format("챌린지 ID %d에서 이미지 ID %d 제거 성공", id, imageId);
        return ResponseEntity.ok(responseMessage);
    }


    // 챌린지 참여 초대
    @PostMapping("/{id}/invite")
    public ResponseEntity<String> inviteToChallenge(@PathVariable Long id, @RequestBody String invitee) {
        // 임의의 초대 데이터 생성
        String invitedUser = "sampleUser@example.com"; // 임의로 초대할 사용자의 이메일

        String responseMessage = String.format("챌린지 ID %d에 사용자 '%s'를 초대했습니다.", id, invitedUser);
        return ResponseEntity.ok(responseMessage);
    }


    // 챌린지 초대 목록 조회
    @GetMapping("/{id}/invitations")
    public ResponseEntity<List<String>> getChallengeInvitations(@PathVariable Long id) {
        // 임의의 초대 목록 데이터 생성
        List<String> invitations = Arrays.asList(
                "user1@example.com",
                "user2@example.com",
                "user3@example.com"
        );

        return ResponseEntity.ok(invitations);
    }



    // 챌린지에 댓글 작성
    // TODO: 댓글 관련 DTO 만드는 것도 고려를 해봐야겠다.
    @PostMapping("/{id}/comments")
    public ResponseEntity<String> addCommentToChallenge(@PathVariable Long id, @RequestBody String comment) {
        // 임의의 댓글 데이터 생성
        String newComment = "새로운 댓글: " + comment;

        return ResponseEntity.ok("챌린지 ID " + id + "에 " + newComment + " 추가됨");
    }


    // 챌린지 댓글 목록 조회
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<String>> getChallengeComments(@PathVariable Long id) {
        // 임의의 댓글 목록 데이터 생성
        List<String> comments = Arrays.asList(
                "챌린지에 대한 첫 번째 댓글",
                "챌린지 진행이 잘 되고 있어요",
                "이 챌린지 너무 흥미로워요"
        );

        return ResponseEntity.ok(comments);
    }


    // 챌린지 규칙 설정
    // TODO: 규칙 관련 DTO 만드는 것도 고려를 해봐야겠다.
    @PostMapping("/{id}/rules")
    public ResponseEntity<String> setChallengeRules(@PathVariable Long id, @RequestBody String rules) {
        // 임의의 규칙 설정 데이터
        String newRules = "챌린지 참가자는 매일 1시간 운동해야 함";

        String responseMessage = String.format("챌린지 ID %d의 규칙 설정 성공: %s", id, newRules);
        return ResponseEntity.ok(responseMessage);
    }



    // 챌린지 규칙 조회
    @GetMapping("/{id}/rules")
    public ResponseEntity<String> getChallengeRules(@PathVariable Long id) {
        // 임의의 챌린지 규칙 데이터
        String challengeRules = "챌린지 참가자는 매일 1시간 운동해야 하며, 매주 1회 보고서를 제출해야 함";

        String responseMessage = String.format("챌린지 ID %d의 규칙 조회 성공: %s", id, challengeRules);
        return ResponseEntity.ok(responseMessage);
    }



    // 챌린지 설정 변경
    @PutMapping("/{id}/settings")
    public ResponseEntity<String> updateChallengeSettings(@PathVariable Long id, @RequestBody ChallengeDto challengeDto) {
        // 임의의 챌린지 설정 변경 데이터
        ChallengeDto updatedSettings = ChallengeDto.builder()
                .title("새로운 챌린지 타이틀")
                .description("이것은 업데이트된 챌린지 설명입니다.")
                .startDate("2023-11-20")
                .endDate("2023-12-20")
                .category("업데이트된 카테고리")
                .difficulty("상")
                .isPublic(false)
                .createdBy("새로운 생성자")
                .build();

        String responseMessage = String.format("챌린지 ID %d 설정 업데이트 성공: %s", id, updatedSettings.getTitle());
        return ResponseEntity.ok(responseMessage);
    }


    // 챌린지 진행 상황 보고
    @PostMapping("/{id}/progress/report")
    public ResponseEntity<String> reportChallengeProgress(@PathVariable Long id, @RequestBody ChallengeProgressReportDto progressReportDto) {
        // 임의의 진행 상황 보고 데이터
        ChallengeProgressReportDto newReport = ChallengeProgressReportDto.builder()
                .challengeId(id)
                .progressDetails("이번 주에는 3번 운동을 완료했습니다.")
                .reportDate("2023-11-15")
                .progressPercentage(75.0) // 진행률 75%
                .build();

        String responseMessage = String.format("챌린지 ID %d 진행 상황 보고: 진행률 - %s%%", id, newReport.getProgressPercentage());
        return ResponseEntity.ok(responseMessage);
    }



    // 챌린지 리더보드 조회
    @GetMapping("/{id}/leaderboard")
    public ResponseEntity<List<LeaderboardEntryDto>> getChallengeLeaderboard(@PathVariable Long id) {
        // 임의의 리더보드 데이터 생성
        List<LeaderboardEntryDto> leaderboard = Arrays.asList(
                LeaderboardEntryDto.builder()
                        .userId(1L)
                        .username("user1")
                        .score(90)
                        .rank(1)
                        .build(),
                LeaderboardEntryDto.builder()
                        .userId(2L)
                        .username("user2")
                        .score(85)
                        .rank(2)
                        .build(),
                LeaderboardEntryDto.builder()
                        .userId(3L)
                        .username("user3")
                        .score(80)
                        .rank(3)
                        .build()
        );

        return ResponseEntity.ok(leaderboard);
    }

    // TODO: 딱히 필요 없어서 제거할 거 같다.
//    // 참여자 필터링 및 관리
//    @PostMapping("/{id}/participants/filter")
//    public ResponseEntity<String> filterParticipants(@PathVariable Long id, @RequestBody ParticipantFilterCriteriaDto filterCriteriaDto) {
//        // 임의의 참여자 필터링 데이터
//        ParticipantFilterCriteriaDto criteria = ParticipantFilterCriteriaDto.builder()
//                .challengeId(id) // 챌린지 ID
//                .filterType("Active") // 필터 유형 (예: "Active", "Completed", "Dropped")
//                .build();
//
//        String responseMessage = String.format("챌린지 ID %d 참여자 필터링: 필터 유형 - %s", id, criteria.getFilterType());
//        return ResponseEntity.ok(responseMessage);
//    }


    // 챌린지 피드백 목록 조회
    @GetMapping("/{id}/feedback")
    public ResponseEntity<List<ChallengeFeedbackDto>> getChallengeFeedback(@PathVariable Long id) {
        // 임의의 챌린지 피드백 목록 생성
        List<ChallengeFeedbackDto> feedbackList = Arrays.asList(
                ChallengeFeedbackDto.builder()
                        .userId(1L)
                        .challengeId(id)
                        .feedback("정말 유익한 챌린지였습니다!")
                        .createdAt("2023-11-01")
                        .build(),
                ChallengeFeedbackDto.builder()
                        .userId(2L)
                        .challengeId(id)
                        .feedback("좋은 경험이었지만, 난이도 조정이 필요해 보입니다.")
                        .createdAt("2023-11-02")
                        .build()
        );

        return ResponseEntity.ok(feedbackList);
    }


    // 챌린지에 대한 피드백 제공
    @PostMapping("/{id}/feedback")
    public ResponseEntity<String> provideFeedbackToChallenge(@PathVariable Long id, @RequestBody ChallengeFeedbackDto challengeFeedbackDto) {
        // 임의의 피드백 데이터 생성
        ChallengeFeedbackDto newFeedback = ChallengeFeedbackDto.builder()
                .userId(123L) // 사용자 ID
                .challengeId(id) // 챌린지 ID
                .feedback("이 챌린지는 매우 도전적이고 유익했습니다.") // 피드백 내용
                .createdAt("2023-11-10") // 피드백 작성 시간
                .build();

        String responseMessage = String.format("챌린지 ID %d에 대한 피드백 제공 성공: %s", id, newFeedback.getFeedback());
        return ResponseEntity.ok(responseMessage);
    }


    // TODO: DTO 관련 처리로 바꾸기
    // 챌린지 관련 분석 데이터 조회
    @GetMapping("/{id}/analytics")
    public ResponseEntity<String> getChallengeAnalytics(@PathVariable Long id) {
        // 임의의 챌린지 분석 데이터 생성
        String analyticsData = String.format("챌린지 ID %d에 대한 분석 데이터:\n" +
                        "- 참여자 수: %d\n" +
                        "- 평균 진행률: %.2f%%\n" +
                        "- 가장 인기 있는 태그: %s\n" +
                        "- 총 피드백 수: %d",
                id, 50, 75.5, "건강", 25);

        return ResponseEntity.ok(analyticsData);
    }


    // TODO: DTO 관련 처리로 바꾸기
    // 챌린지 보고서 및 분석
    @GetMapping("/{id}/reports")
    public ResponseEntity<String> getChallengeReports(@PathVariable Long id) {
        // 임의의 챌린지 보고서 데이터 생성
        String reportsData = String.format("챌린지 ID %d에 대한 보고서:\n" +
                        "- 최고 성과자: %s\n" +
                        "- 평균 일일 진행 시간: %.1f 시간\n" +
                        "- 가장 활발한 토론 주제: %s\n" +
                        "- 챌린지 완료율: %.2f%%",
                id, "User123", 2.5, "건강한 생활 습관", 80.0);

        return ResponseEntity.ok(reportsData);
    }


    // TODO: DTO 관련 처리로 바꾸기
    // 챌린지 보고서 생성
    @PostMapping("/{id}/reports/generate")
    public ResponseEntity<String> generateChallengeReport(@PathVariable Long id) {
        // 임의의 챌린지 보고서 생성 데이터
        String reportDetails = String.format("챌린지 ID %d에 대한 보고서 생성 요청:\n" +
                        "- 요청한 데이터 포인트: %s\n" +
                        "- 보고서 생성 예상 시간: %s",
                id, "참여자 통계, 진행률, 피드백 요약", "약 30분");

        return ResponseEntity.ok(reportDetails);
    }


    // TODO: DTO 관련 처리로 바꾸기
    // 참여자 상세 정보 조회
    @GetMapping("/{id}/participants/details")
    public ResponseEntity<List<String>> getParticipantsDetails(@PathVariable Long id) {
        // 임의의 참여자 상세 정보 생성
        List<String> participantDetails = Arrays.asList(
                String.format("참여자 ID: %d, 이름: %s, 참여 상태: %s, 시작 날짜: %s, 종료 날짜: %s",
                        101, "참여자1", "활성", "2023-11-01", "2023-12-01"),
                String.format("참여자 ID: %d, 이름: %s, 참여 상태: %s, 시작 날짜: %s, 종료 날짜: %s",
                        102, "참여자2", "완료", "2023-10-01", "2023-11-01"),
                String.format("참여자 ID: %d, 이름: %s, 참여 상태: %s, 시작 날짜: %s, 종료 날짜: %s",
                        103, "참여자3", "중단", "2023-09-01", "2023-10-01")
        );

        return ResponseEntity.ok(participantDetails);
    }



    // TODO: DTO 관련 처리로 바꾸기
    // 참여자 관리
    @PostMapping("/{id}/participants/manage")
    public ResponseEntity<String> manageParticipants(@PathVariable Long id, @RequestBody ChallengeParticipationDto participationDto) {
        // 임의의 참여자 관리 데이터 생성
        ChallengeParticipationDto manageParticipant = ChallengeParticipationDto.builder()
                .userId(participationDto.getUserId()) // 참여자 ID
                .challengeId(id) // 챌린지 ID
                .participationStatus("활성화") // 참여 상태 변경
                .build();

        String responseMessage = String.format("참여자 ID %d의 참여 상태가 '%s'로 변경됨 (챌린지 ID: %d)",
                manageParticipant.getUserId(), manageParticipant.getParticipationStatus(), id);
        return ResponseEntity.ok(responseMessage);
    }

}
