package daehee.challengehub.challenge.verification.repository;

import com.mongodb.client.result.DeleteResult;
import daehee.challengehub.challenge.verification.entity.Verification;
import daehee.challengehub.challenge.verification.model.VerificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class VerificationRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public VerificationRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // 인증 정보 저장
    public Verification saveVerification(VerificationDto verificationDto) {
        Verification verification = Verification.builder()
                .challengeId(verificationDto.getChallengeId())
                .participantId(verificationDto.getParticipantId())
                .imageUrl(verificationDto.getImageUrl())
                .timestamp(Instant.now()) // TODO: 참여자가 보낸 시점으로 시간대 생성, 로직에 따라 바뀔 수도 있음
                .isVerified(verificationDto.getIsVerified())
                .build();

        return mongoTemplate.save(verification, "verifications");
    }

    // 특정 챌린지에 대한 모든 인증 정보 조회
    public List<Verification> findAllByChallengeId(String challengeId) {
        Query query = new Query(Criteria.where("challengeId").is(challengeId));
        return mongoTemplate.find(query, Verification.class, "verifications");
    }

    // 특정 참가자의 인증 정보 조회
    public List<Verification> findByParticipantId(String participantId) {
        Query query = new Query(Criteria.where("participantId").is(participantId));
        return mongoTemplate.find(query, Verification.class, "verifications");
    }

    // 특정 인증 정보 조회
    public Verification findById(String id) {
        return mongoTemplate.findById(id, Verification.class, "verifications");
    }

    // 특정 인증 정보 업데이트
    // TODO: 이건 챌린지 리더가 변경 해야하기 위해서 필요한 부분인데 어떻게 비즈니스 로직 처리해야할 지를 Service 파트에서 생각을 해봐야할 듯
    public Verification updateVerification(String id, VerificationDto updatedVerificationDto) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("isVerified", updatedVerificationDto.getIsVerified());
        return mongoTemplate.findAndModify(query, update, Verification.class, "verifications");
    }

    // 특정 인증 정보 삭제
    public boolean deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, Verification.class, "verifications");
        return deleteResult.getDeletedCount() > 0;
    }
}


