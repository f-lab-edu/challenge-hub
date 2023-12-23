package daehee.challengehub.user.authentication.repository;

import daehee.challengehub.user.authentication.entity.User;
import daehee.challengehub.user.authentication.model.UserSignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AuthenticationRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // 사용자 정보 저장
    public void save(UserSignupDto user) {
        mongoTemplate.save(user, "users");
    }

    // 이메일을 기반으로 사용자 정보 조회
    public User findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class, "users");
    }

    // 토큰을 기반으로 이메일 인증 상태 조회
    public boolean isEmailVerified(String token) {
        // MongoDB에서 token을 통해 이메일 인증 상태 확인
        // 이 예시에서는 단순화를 위해 항상 true 반환
        return true;
    }

    // 로그인 정보 확인
    public boolean validateLogin(String email, String password) {
        // MongoDB에서 이메일과 비밀번호가 일치하는 사용자 조회
        User user = findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // 비밀번호 재설정 관련 로직
    public void updatePassword(String email, String newPassword) {
        // MongoDB에서 사용자의 비밀번호 업데이트
        Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update().set("password", newPassword);
        mongoTemplate.updateFirst(query, update, UserSignupDto.class, "users");
    }
}
