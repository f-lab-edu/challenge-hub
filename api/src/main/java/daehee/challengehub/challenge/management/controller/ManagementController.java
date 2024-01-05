package daehee.challengehub.challenge.management.controller;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.entity.Participant;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.challenge.management.model.ParticipantDto;
import daehee.challengehub.challenge.management.service.ManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // 챌린지 생성
    @PostMapping
    public ChallengeDto createChallenge(@RequestBody ChallengeDto challengeData) {
        Challenge createdChallenge = managementService.createChallenge(challengeData);
        return modelMapper.map(createdChallenge, ChallengeDto.class);
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
    @GetMapping("/{id}")
    public ChallengeDto getChallengeById(@PathVariable String id) {
        Challenge challenge = managementService.getChallengeById(id);
        return modelMapper.map(challenge, ChallengeDto.class);
    }

    // 특정 챌린지 수정
    @PutMapping("/{id}")
    public ChallengeDto updateChallenge(@PathVariable String id, @RequestBody ChallengeDto challengeData) {
        Challenge updatedChallenge = managementService.updateChallenge(id, challengeData);
        // 수정된 엔터티를 DTO로 변환하여 반환
        return modelMapper.map(updatedChallenge, ChallengeDto.class);
    }

    // 챌린지 삭제
    // TODO: 삭제는 실제로 삭제를 하는 것일까 아니면 삭제한 "척"을 하는 것일까?
    @DeleteMapping("/{id}")
    public boolean deleteChallenge(@PathVariable String id) {
        return managementService.deleteChallenge(id);
    }


    // 챌린지 참여 신청
    @PostMapping("/{id}/participation")
    public ParticipantDto participateInChallenge(@PathVariable String id, @RequestBody String userId) {
        Participant participant = managementService.participateInChallenge(id, userId);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    // 챌린지 참여 취소
    @DeleteMapping("/{id}/participation")
    public boolean cancelParticipation(@PathVariable String id, @RequestBody String userId) {
        return managementService.cancelParticipation(id, userId);
    }

    // 챌린지 참가자 목록 조회
    @GetMapping("/{id}/participants")
    public List<ParticipantDto> getChallengeParticipants(@PathVariable String id) {
        List<Participant> participants = managementService.getChallengeParticipants(id);
        return participants.stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
                .toList();
    }
}
