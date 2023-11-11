package daehee.challengehub.controller;

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
import daehee.challengehub.service.ChallengeService;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // 챌린지 생성
    @PostMapping
    public ResponseEntity<String> createChallenge() {
        return ResponseEntity.ok(challengeService.createChallenge());
    }

    // 챌린지 목록 조회
    @GetMapping
    public ResponseEntity<String> getAllChallenges() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    // 특정 챌린지 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<String> getChallengeById(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeById(id));
    }

    // 챌린지 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.updateChallenge(id));
    }

    // 챌린지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.deleteChallenge(id));
    }

    // 챌린지 참여
    @PostMapping("/{id}/participation")
    public ResponseEntity<String> participateInChallenge(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.participateInChallenge(id));
    }

    // 챌린지 진행 상태 조회
    @GetMapping("/{id}/progress")
    public ResponseEntity<String> getChallengeProgress(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeProgress(id));
    }

    // 챌린지 성과 등록
    @PostMapping("/{id}/achievement")
    public ResponseEntity<String> addChallengeAchievement(@PathVariable Long id, @RequestBody String achievement) {
        return ResponseEntity.ok(challengeService.addChallengeAchievement(id, achievement));
    }

    // 챌린지에 태그 추가
    @PostMapping("/{id}/tags")
    public ResponseEntity<String> addTagToChallenge(@PathVariable Long id, @RequestBody String tag) {
        return ResponseEntity.ok(challengeService.addTagToChallenge(id, tag));
    }

    // 챌린지에서 태그 제거
    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<String> removeTagFromChallenge(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok(challengeService.removeTagFromChallenge(id, tagId));
    }

    // 챌린지에 이미지 업로드
    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadImageToChallenge(@PathVariable Long id, @RequestBody String imageUrl) {
        return ResponseEntity.ok(challengeService.uploadImageToChallenge(id, imageUrl));
    }

    // 챌린지에서 이미지 제거
    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseEntity<String> removeImageFromChallenge(@PathVariable Long id, @PathVariable Long imageId) {
        return ResponseEntity.ok(challengeService.removeImageFromChallenge(id, imageId));
    }

    // 챌린지 참여 초대
    @PostMapping("/{id}/invite")
    public ResponseEntity<String> inviteToChallenge(@PathVariable Long id, @RequestBody String invitee) {
        return ResponseEntity.ok(challengeService.inviteToChallenge(id, invitee));
    }

    // 챌린지 초대 목록 조회
    @GetMapping("/{id}/invitations")
    public ResponseEntity<String> getChallengeInvitations(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeInvitations(id));
    }

    // 챌린지에 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<String> addCommentToChallenge(@PathVariable Long id, @RequestBody String comment) {
        return ResponseEntity.ok(challengeService.addCommentToChallenge(id, comment));
    }

    // 챌린지 댓글 목록 조회
    @GetMapping("/{id}/comments")
    public ResponseEntity<String> getChallengeComments(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeComments(id));
    }

    // 챌린지 규칙 설정
    @PostMapping("/{id}/rules")
    public ResponseEntity<String> setChallengeRules(@PathVariable Long id, @RequestBody String rules) {
        return ResponseEntity.ok(challengeService.setChallengeRules(id, rules));
    }

    // 챌린지 규칙 조회
    @GetMapping("/{id}/rules")
    public ResponseEntity<String> getChallengeRules(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeRules(id));
    }

    // 챌린지 설정 변경
    @PutMapping("/{id}/settings")
    public ResponseEntity<String> updateChallengeSettings(@PathVariable Long id, @RequestBody String settings) {
        return ResponseEntity.ok(challengeService.updateChallengeSettings(id, settings));
    }

    // 챌린지 진행 상황 보고
    @PostMapping("/{id}/progress/report")
    public ResponseEntity<String> reportChallengeProgress(@PathVariable Long id, @RequestBody String progressReport) {
        return ResponseEntity.ok(challengeService.reportChallengeProgress(id, progressReport));
    }

    // 챌린지 리더보드 조회
    @GetMapping("/{id}/leaderboard")
    public ResponseEntity<String> getChallengeLeaderboard(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeLeaderboard(id));
    }

    // 참여자 필터링 및 관리
    @PostMapping("/{id}/participants/filter")
    public ResponseEntity<String> filterParticipants(@PathVariable Long id, @RequestBody String filterCriteria) {
        return ResponseEntity.ok(challengeService.filterParticipants(id, filterCriteria));
    }

    // 챌린지 피드백 목록 조회
    @GetMapping("/{id}/feedback")
    public ResponseEntity<List<String>> getChallengeFeedback(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeFeedback(id));
    }

    // 챌린지에 대한 피드백 제공
    @PostMapping("/{id}/feedback")
    public ResponseEntity<String> provideFeedbackToChallenge(@PathVariable Long id, @RequestBody String feedback) {
        return ResponseEntity.ok(challengeService.provideFeedbackToChallenge(id, feedback));
    }

    // 챌린지 관련 분석 데이터 조회
    @GetMapping("/{id}/analytics")
    public ResponseEntity<String> getChallengeAnalytics(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeAnalytics(id));
    }

    // 챌린지 보고서 및 분석
    @GetMapping("/{id}/reports")
    public ResponseEntity<String> getChallengeReports(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeReports(id));
    }

    // 챌린지 보고서 생성
    @PostMapping("/{id}/reports/generate")
    public ResponseEntity<String> generateChallengeReport(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.generateChallengeReport(id));
    }

    // 참여자 상세 정보 조회
    @GetMapping("/{id}/participants/details")
    public ResponseEntity<String> getParticipantsDetails(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getParticipantsDetails(id));
    }

    // 참여자 관리
    @PostMapping("/{id}/participants/manage")
    public ResponseEntity<String> manageParticipants(@PathVariable Long id, @RequestBody String participantAction) {
        return ResponseEntity.ok(challengeService.manageParticipants(id, participantAction));
    }
}
