package daehee.challengehub.interaction.repository;

import daehee.challengehub.interaction.model.ChallengeCommentDto;
import daehee.challengehub.interaction.model.ChallengeParticipantDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InteractionRepository {
    private final Map<Long, List<ChallengeCommentDto>> challengeComments = new HashMap<>();
    private final Map<Long, List<ChallengeParticipantDto>> challengeLeaderboards = new HashMap<>();
    private final Map<Long, List<ChallengeParticipantDto>> challengeParticipants = new HashMap<>();
    private long commentIdCounter = 1;

    public InteractionRepository() {

    }

    public void postComment(Long challengeId, ChallengeCommentDto comment) {
        comment.setCommentId(commentIdCounter++);
        challengeComments.computeIfAbsent(challengeId, k -> new ArrayList<>()).add(comment);
    }

    public List<ChallengeCommentDto> getComments(Long challengeId) {
        return challengeComments.getOrDefault(challengeId, new ArrayList<>());
    }

    public List<ChallengeParticipantDto> getLeaderboard(Long challengeId) {
        return challengeLeaderboards.getOrDefault(challengeId, new ArrayList<>());
    }

    public List<ChallengeParticipantDto> getParticipants(Long challengeId) {
        return challengeParticipants.getOrDefault(challengeId, new ArrayList<>());
    }

    public void manageParticipants(Long challengeId, ChallengeParticipantDto participantData) {
        // 참여자 관리 로직
    }

}

