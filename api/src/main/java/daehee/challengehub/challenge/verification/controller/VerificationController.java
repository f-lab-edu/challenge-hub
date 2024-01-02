package daehee.challengehub.challenge.verification.controller;

import daehee.challengehub.challenge.verification.entity.Verification;
import daehee.challengehub.challenge.verification.model.VerificationDto;
import daehee.challengehub.challenge.verification.service.VerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/verifications")
public class VerificationController {

    private final VerificationService verificationService;
    private final ModelMapper modelMapper;

    @Autowired
    public VerificationController(VerificationService verificationService, ModelMapper modelMapper) {
        this.verificationService = verificationService;
        this.modelMapper = modelMapper;
    }

    // 인증 정보 저장
    @PostMapping
    public VerificationDto createVerification(@RequestBody VerificationDto verificationDto) {
        Verification savedVerification = verificationService.saveVerification(verificationDto);
        return modelMapper.map(savedVerification, VerificationDto.class);
    }

    // 특정 챌린지에 대한 모든 인증 정보 조회
    @GetMapping("/challenge/{challengeId}")
    public List<VerificationDto> getAllVerificationsByChallengeId(@PathVariable String challengeId) {
        List<Verification> verifications = verificationService.findAllByChallengeId(challengeId);
        return verifications.stream()
                .map(verification -> modelMapper.map(verification, VerificationDto.class))
                .toList();
    }

    // 특정 참가자의 인증 정보 조회
    @GetMapping("/participant/{participantId}")
    public List<VerificationDto> getVerificationsByParticipantId(@PathVariable String participantId) {
        List<Verification> verifications = verificationService.findByParticipantId(participantId);
        return verifications.stream()
                .map(verification -> modelMapper.map(verification, VerificationDto.class))
                .toList();
    }

    // 특정 인증 정보 조회
    @GetMapping("/{id}")
    public VerificationDto getVerificationById(@PathVariable String id) {
        Verification verification = verificationService.findById(id);
        return modelMapper.map(verification, VerificationDto.class);
    }

    // 특정 인증 정보 업데이트
    @PutMapping("/{id}")
    public VerificationDto updateVerification(@PathVariable String id, @RequestBody VerificationDto verificationDto) {
        Verification updatedVerification = verificationService.updateVerification(id, verificationDto);
        return modelMapper.map(updatedVerification, VerificationDto.class);
    }

    // 특정 인증 정보 삭제
    @DeleteMapping("/{id}")
    public boolean deleteVerification(@PathVariable String id) {
        return verificationService.deleteById(id);
    }
}

