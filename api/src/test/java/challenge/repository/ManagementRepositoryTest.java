package challenge.repository;

import daehee.challengehub.challenge.management.entity.Challenge;
import daehee.challengehub.challenge.management.repository.ManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

// TODO: MongoDB Driver 쓰는 경우 안 쓰는 경우의 페이지네이션의 성능 측정을 위해서 작성함
@SpringBootTest
public class ManagementRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ManagementRepository managementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // MongoDB 드라이버 사용 성능 테스트
    @Test
    public void testWithMongoDriver() {
        String lastId = ""; // 초기값 설정
        int limit = 50;

        // Mockito 동작 정의
        when(mongoTemplate.find(any(Query.class), eq(Challenge.class), eq("challenges")))
                .thenReturn(new ArrayList<>()); // TODO: 채워야됨, 빈 결과 반환

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            managementRepository.getAllChallengesWithMongoDriver(lastId, limit);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("With MongoDB Driver Time: " + (endTime - startTime) + " ms");
    }

    // MongoDB 드라이버 미사용 성능 테스트
    @Test
    public void testWithoutMongoDriver() {
        String lastId = ""; // 초기값 설정
        int limit = 50;

        // Mockito 동작 정의
        when(mongoTemplate.find(any(Query.class), eq(Challenge.class), eq("challenges")))
                .thenReturn(new ArrayList<>()); // TODO: 채워야됨, 빈 결과 반환

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            managementRepository.getAllChallengesWithoutMongoDriver(lastId, limit);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Without MongoDB Driver Time: " + (endTime - startTime) + " ms");
    }
}
