package daehee.challengehub.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    private final Environment env;

    @Autowired
    public MongoConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        String username = env.getProperty("MONGO_INITDB_ROOT_USERNAME");
        String password = env.getProperty("MONGO_INITDB_ROOT_PASSWORD");
        String uri = "mongodb://" + username + ":" + password + "@localhost:27017/";
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
    }
}

