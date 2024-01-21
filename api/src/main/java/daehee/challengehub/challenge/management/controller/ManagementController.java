package daehee.challengehub.challenge.management.controller;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.entity.Participant;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.challenge.management.model.ParticipantDto;
import daehee.challengehub.challenge.management.service.ManagementService;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/challenges")
public class ManagementController {
    private final ManagementService managementService;
    private final ModelMapper modelMapper;

    @Autowired
    public ManagementController(ManagementService managementService, ModelMapper modelMapper) {
        this.managementService = managementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ChallengeDto createChallenge(@RequestBody ChallengeDto challengeData) {
        try {
            challengeData.validate();
            return managementService.createChallenge(challengeData)
                    .thenApplyAsync(challenge -> modelMapper.map(challenge, ChallengeDto.class))
                    .join();
        } catch (ValidationException | InterruptedException | ExecutionException e) {
            // TODO: 유효성 검사 실패 시 처리
            // - 로그 기록
            // - 사용자에게 오류 메시지 반환
            return null;
        }
    }



    // 전체 챌린지 목록 조회 with 인피니트 스크롤 & 커서 기반 페이지네이션
    @GetMapping
    public List<ChallengeDto> getAllChallenges(@RequestParam(required = false) String lastId,
                                               @RequestParam(defaultValue = "10") int limit) {
        List<Challenge> challenges = managementService.getAllChallenges(lastId, limit);
        return challenges.stream()
                .map(challenge -> modelMapper.map(challenge, ChallengeDto.class))
                .toList();
    }

    // 특정 챌린지 상세 조회
    // TODO: 이건 API를 또 나눠야할까? 아니면 API를 또 나눌 필요가 없을까?
    @GetMapping("/{challengeId}")
    public ChallengeDto getChallengeById(@PathVariable String challengeId) {
        Challenge challenge = managementService.getChallengeById(challengeId);
        return modelMapper.map(challenge, ChallengeDto.class);
    }

    // 특정 챌린지 수정 (부분 업데이트)
    @PatchMapping("/{challengeId}")
    public ChallengeDto updateChallengePartially(@PathVariable String challengeId, @RequestBody ChallengeDto challengeData) {
        Challenge updatedChallenge = managementService.updateChallenge(challengeId, challengeData, false);
        return modelMapper.map(updatedChallenge, ChallengeDto.class);
    }

    // 특정 챌린지 수정 (전체 업데이트)
    @PutMapping("/{challengeId}")
    public ChallengeDto updateChallengeFully(@PathVariable String challengeId, @RequestBody ChallengeDto challengeData) {
        Challenge updatedChallenge = managementService.updateChallenge(challengeId, challengeData, true);
        return modelMapper.map(updatedChallenge, ChallengeDto.class);
    }

    // 챌린지 삭제
    // TODO: 삭제는 실제로 삭제를 하는 것일까 아니면 삭제한 "척"을 하는 것일까?
    @DeleteMapping("/{challengeId}")
    public boolean deleteChallenge(@PathVariable String challengeId) {
        return managementService.deleteChallenge(challengeId);
    }


    // 챌린지 참여 신청
    @PostMapping("/{challengeId}/participation")
    public ParticipantDto participateInChallenge(@PathVariable String challengeId, @RequestBody String userId) {
        Participant participant = managementService.participateInChallenge(challengeId, userId);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    // 챌린지 참여 취소
    @DeleteMapping("/{challengeId}/participation")
    public boolean cancelParticipation(@PathVariable String challengeId, @RequestBody String userId) {
        return managementService.cancelParticipation(challengeId, userId);
    }

    // 챌린지 참가자 목록 조회
    @GetMapping("/{challengeId}/participants")
    public List<ParticipantDto> getChallengeParticipants(@PathVariable String challengeId) {
        List<Participant> participants = managementService.getChallengeParticipants(challengeId);
        return participants.stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
                .toList();
    }
}
