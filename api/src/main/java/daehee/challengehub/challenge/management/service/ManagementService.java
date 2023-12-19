package daehee.challengehub.challenge.management.service;


import daehee.challengehub.challenge.management.model.AddTagResponseDto;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.challenge.management.model.ChallengeImageDto;
import daehee.challengehub.challenge.management.model.ChallengeTagDto;
import daehee.challengehub.challenge.management.model.CreateChallengeResponseDto;
import daehee.challengehub.challenge.management.model.DeleteChallengeResponseDto;
import daehee.challengehub.challenge.management.model.GetAllChallengesResponseDto;
import daehee.challengehub.challenge.management.model.GetChallengeResponseDto;
import daehee.challengehub.challenge.management.model.ParticipateInChallengeResponseDto;
import daehee.challengehub.challenge.management.model.RemoveImageResponseDto;
import daehee.challengehub.challenge.management.model.RemoveTagResponseDto;
import daehee.challengehub.challenge.management.model.UpdateChallengeResponseDto;
import daehee.challengehub.challenge.management.model.UploadImageResponseDto;
import daehee.challengehub.challenge.management.repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementService {

    private final ManagementRepository managementRepository;

    @Autowired
    public ManagementService(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    public CreateChallengeResponseDto createChallenge(ChallengeDto challengeData) {
        managementRepository.createChallenge(challengeData);
        return new CreateChallengeResponseDto("챌린지 생성 성공", challengeData);
    }

    public GetAllChallengesResponseDto getAllChallenges() {
        List<ChallengeDto> challenges = managementRepository.getAllChallenges();
        return new GetAllChallengesResponseDto(challenges);
    }

    public GetChallengeResponseDto getChallengeById(Long challengeId) {
        ChallengeDto challenge = managementRepository.getChallengeById(challengeId);
        return new GetChallengeResponseDto(challenge);
    }

    public UpdateChallengeResponseDto updateChallenge(Long challengeId, ChallengeDto challengeData) {
        managementRepository.updateChallenge(challengeId, challengeData);
        return new UpdateChallengeResponseDto("챌린지 수정 성공", challengeData);
    }

    public DeleteChallengeResponseDto deleteChallenge(Long challengeId) {
        managementRepository.deleteChallenge(challengeId);
        return new DeleteChallengeResponseDto("챌린지 삭제 성공: 챌린지 ID " + challengeId);
    }

    public AddTagResponseDto addTagToChallenge(Long challengeId, ChallengeTagDto tagData) {
        managementRepository.addTagToChallenge(challengeId, tagData);
        return new AddTagResponseDto("태그 추가 성공", tagData);
    }

    public RemoveTagResponseDto removeTagFromChallenge(Long challengeId, Long tagId) {
        managementRepository.removeTagFromChallenge(challengeId, tagId);
        return new RemoveTagResponseDto("태그 삭제 성공: 태그 ID " + tagId + " 챌린지 ID " + challengeId);
    }

    public UploadImageResponseDto uploadImageToChallenge(Long challengeId, ChallengeImageDto imageData) {
        managementRepository.uploadImageToChallenge(challengeId, imageData);
        return new UploadImageResponseDto("이미지 업로드 성공", imageData);
    }

    public RemoveImageResponseDto removeImageFromChallenge(Long challengeId, Long imageId) {
        managementRepository.removeImageFromChallenge(challengeId, imageId);
        return new RemoveImageResponseDto("이미지 삭제 성공: 이미지 ID " + imageId + " 챌린지 ID " + challengeId);
    }

    public ParticipateInChallengeResponseDto participateInChallenge(Long challengeId) {
        // TODO: 참가자 데이터 처리 로직 구현 필요
        return new ParticipateInChallengeResponseDto("챌린지 참여 성공: 챌린지 ID " + challengeId);
    }
}
