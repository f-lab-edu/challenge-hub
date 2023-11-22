package daehee.challengehub.management.repository;

import daehee.challengehub.management.model.ChallengeDto;
import daehee.challengehub.management.model.ChallengeImageDto;
import daehee.challengehub.management.model.ChallengeTagDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class ManagementRepository {
    private final Map<Long, ChallengeDto> challenges = new HashMap<>();
    private final Map<Long, ChallengeTagDto> challengeTags = new HashMap<>();
    private final Map<Long, ChallengeImageDto> challengeImages = new HashMap<>();
    private long challengeIdCounter = 1;
    private long tagIdCounter = 1;
    private long imageIdCounter = 1;

    public ManagementRepository() {

    }

    public void createChallenge(ChallengeDto challengeData) {
        challengeData.setChallengeId(challengeIdCounter++);
        challenges.put(challengeData.getChallengeId(), challengeData);
    }

    public ChallengeDto getChallengeById(Long challengeId) {
        return challenges.get(challengeId);
    }

    public List<ChallengeDto> getAllChallenges() {
        return new ArrayList<>(challenges.values());
    }

    public void updateChallenge(Long challengeId, ChallengeDto challengeData) {
        challenges.put(challengeId, challengeData);
    }

    public void deleteChallenge(Long challengeId) {
        challenges.remove(challengeId);
    }

    public void addTagToChallenge(Long challengeId, ChallengeTagDto tagData) {
        tagData.setTagId(tagIdCounter++);
        challengeTags.put(tagData.getTagId(), tagData);
        // 챌린지에 태그 연결 로직 (추가 필요)
    }

    public void removeTagFromChallenge(Long challengeId, Long tagId) {
        challengeTags.remove(tagId);
        // 챌린지에서 태그 연결 해제 로직 (추가 필요)
    }

    public void uploadImageToChallenge(Long challengeId, ChallengeImageDto imageData) {
        imageData.setImageId(imageIdCounter++);
        challengeImages.put(imageData.getImageId(), imageData);
        // 챌린지에 이미지 연결 로직 (추가 필요)
    }

    public void removeImageFromChallenge(Long challengeId, Long imageId) {
        challengeImages.remove(imageId);
        // 챌린지에서 이미지 연결 해제 로직 (추가 필요)
    }

}
