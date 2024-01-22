package daehee.challengehub.challenge.management.repository;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.entity.Participant;
import daehee.challengehub.challenge.management.model.ChallengeDto;
import daehee.challengehub.common.constants.ErrorCode;
import daehee.challengehub.common.exception.CustomException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class ManagementRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ManagementRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // 챌린지 생성, v1
    public Challenge createChallengeV1(ChallengeDto challengeDto) {
        Challenge challenge = Challenge.builder()
                .title(challengeDto.getTitle())
                .frequency(challengeDto.getFrequency())
                .duration(challengeDto.getDuration())
                .startTime(challengeDto.getStartTime())
                .endTime(challengeDto.getEndTime())
                .startDate(challengeDto.getStartDate())
                .verificationMethod(challengeDto.getVerificationMethod())
                .verificationExampleUrls(challengeDto.getVerificationExampleUrls())
                .isCameraOnly(challengeDto.getIsCameraOnly())
                .description(challengeDto.getDescription())
                .category(challengeDto.getCategory())
                .coverImageUrl(challengeDto.getCoverImageUrl())
                .keywords(challengeDto.getKeywords())
                .isPublic(challengeDto.getIsPublic())
                .createdBy(challengeDto.getCreatedBy())
                .createdAt(Instant.now())
                .lastModified(Instant.now())
                .build();

        return mongoTemplate.save(challenge);
    }


    // 챌린지 생성, v2
    public Challenge createChallengeV2(ChallengeDto challengeDto) {
        mongoTemplate.indexOps(Challenge.class).ensureIndex(new Index().on("title", Sort.Direction.ASC));
        if (mongoTemplate.exists(Query.query(Criteria.where("title").is(challengeDto.getTitle())), Challenge.class)) {
            throw new IllegalStateException("이미 존재하는 챌린지 제목입니다.");
        }

        Challenge challenge = Challenge.builder()
                .title(challengeDto.getTitle())
                .frequency(challengeDto.getFrequency())
                .duration(challengeDto.getDuration())
                .startTime(challengeDto.getStartTime())
                .endTime(challengeDto.getEndTime())
                .startDate(challengeDto.getStartDate())
                .verificationMethod(challengeDto.getVerificationMethod())
                .verificationExampleUrls(challengeDto.getVerificationExampleUrls())
                .isCameraOnly(challengeDto.getIsCameraOnly())
                .description(challengeDto.getDescription())
                .category(challengeDto.getCategory())
                .coverImageUrl(challengeDto.getCoverImageUrl())
                .keywords(challengeDto.getKeywords())
                .isPublic(challengeDto.getIsPublic())
                .createdBy(challengeDto.getCreatedBy())
                .createdAt(Instant.now())
                .lastModified(Instant.now())
                .build();

        return mongoTemplate.save(challenge);
    }

    // 전체 챌린지 목록 조회
    public List<Challenge> getAllChallenges(String lastId, int limit) {
        Query query = new Query();
        if (lastId != null && !lastId.isEmpty()) {
            query.addCriteria(Criteria.where("_id").gt(new ObjectId(lastId)));
        }
        query.limit(limit).with(Sort.by(Sort.Direction.DESC, "_id"));
        return mongoTemplate.find(query, Challenge.class);
    }


    // 특정 챌린지 상세 조회
    public Challenge getChallengeById(String challengeId) {
        return mongoTemplate.findById(challengeId, Challenge.class);
    }

    // 특정 챌린지 수정
    public Challenge updateChallenge(String challengeId, ChallengeDto updatedChallengeDto, boolean isFullUpdate) {
        if (challengeId == null) {
            throw new CustomException(ErrorCode.CHALLENGE_ID_REQUIRED);
        }

        Query query = new Query(Criteria.where("_id").is(challengeId));
        Update update = new Update();

        // 전체 업데이트인 경우 모든 필드를 업데이트
        if (isFullUpdate) {
            update.set("title", updatedChallengeDto.getTitle());
            update.set("frequency", updatedChallengeDto.getFrequency());
            update.set("duration", updatedChallengeDto.getDuration());
            update.set("startTime", updatedChallengeDto.getStartTime());
            update.set("endTime", updatedChallengeDto.getEndTime());
            update.set("startDate", updatedChallengeDto.getStartDate());
            update.set("verificationMethod", updatedChallengeDto.getVerificationMethod());
            update.set("verificationExampleUrls", updatedChallengeDto.getVerificationExampleUrls());
            update.set("isCameraOnly", updatedChallengeDto.getIsCameraOnly());
            update.set("description", updatedChallengeDto.getDescription());
            update.set("category", updatedChallengeDto.getCategory());
            update.set("coverImageUrl", updatedChallengeDto.getCoverImageUrl());
            update.set("keywords", updatedChallengeDto.getKeywords());
            update.set("isPublic", updatedChallengeDto.getIsPublic());
            update.set("createdBy", updatedChallengeDto.getCreatedBy());
            update.set("createdAt", updatedChallengeDto.getCreatedAt());
            update.set("lastModified", Instant.now());
        } else {
            if (updatedChallengeDto.getTitle() != null) {
                update.set("title", updatedChallengeDto.getTitle());
            }
            if (updatedChallengeDto.getFrequency() != null) {
                update.set("frequency", updatedChallengeDto.getFrequency());
            }
            if (updatedChallengeDto.getDuration() != null) {
                update.set("duration", updatedChallengeDto.getDuration());
            }
            if (updatedChallengeDto.getStartTime() != null) {
                update.set("startTime", updatedChallengeDto.getStartTime());
            }
            if (updatedChallengeDto.getEndTime() != null) {
                update.set("endTime", updatedChallengeDto.getEndTime());
            }
            if (updatedChallengeDto.getStartDate() != null) {
                update.set("startDate", updatedChallengeDto.getStartDate());
            }
            if (updatedChallengeDto.getVerificationMethod() != null) {
                update.set("verificationMethod", updatedChallengeDto.getVerificationMethod());
            }
            if (updatedChallengeDto.getVerificationExampleUrls() != null) {
                update.set("verificationExampleUrls", updatedChallengeDto.getVerificationExampleUrls());
            }
            if (updatedChallengeDto.getIsCameraOnly() != null) {
                update.set("isCameraOnly", updatedChallengeDto.getIsCameraOnly());
            }
            if (updatedChallengeDto.getDescription() != null) {
                update.set("description", updatedChallengeDto.getDescription());
            }
            if (updatedChallengeDto.getCategory() != null) {
                update.set("category", updatedChallengeDto.getCategory());
            }
            if (updatedChallengeDto.getCoverImageUrl() != null) {
                update.set("coverImageUrl", updatedChallengeDto.getCoverImageUrl());
            }
            if (updatedChallengeDto.getKeywords() != null) {
                update.set("keywords", updatedChallengeDto.getKeywords());
            }
            if (updatedChallengeDto.getIsPublic() != null) {
                update.set("isPublic", updatedChallengeDto.getIsPublic());
            }
            if (updatedChallengeDto.getCreatedBy() != null) {
                update.set("createdBy", updatedChallengeDto.getCreatedBy());
            }
            if (updatedChallengeDto.getCreatedAt() != null) {
                update.set("createdAt", updatedChallengeDto.getCreatedAt());
            }
        }

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);

        return mongoTemplate.findAndModify(query, update, options, Challenge.class);
    }

    // 챌린지 삭제
    public boolean deleteChallenge(String challengeId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId));
        return mongoTemplate.remove(query, Challenge.class).getDeletedCount() > 0;
    }

    // 챌린지 참여 신청
    public Participant participateInChallenge(String challengeId, String userId) {
        Participant participant = Participant.builder()
                .challengeId(challengeId)
                .userId(userId)
                .joinDate(Instant.now())
                .achievementRate(0.0)
                .build();

        return mongoTemplate.save(participant);
    }

    // 챌린지 참여 취소
    public boolean cancelParticipation(String challengeId, String userId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId)
                .andOperator(Criteria.where("userId").is(userId)));
        return mongoTemplate.remove(query, Participant.class).getDeletedCount() > 0;
    }

    // 챌린지 참가자 목록 조회
    public List<Participant> getChallengeParticipants(String challengeId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId));
        return mongoTemplate.find(query, Participant.class);
    }
}
