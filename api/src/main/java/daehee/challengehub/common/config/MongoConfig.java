package daehee.challengehub.common.config;

import daehee.challengehub.common.util.LoggerUtil;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    private final Dotenv dotenv;

    @Autowired
    public MongoConfig(Dotenv dotenv) {
        this.dotenv = dotenv;
        LoggerUtil.info(this.getClass().getSimpleName(), "MongoConfig","MongoDB Username: " + dotenv.get("MONGO_INITDB_ROOT_USERNAME"));
        LoggerUtil.info(this.getClass().getSimpleName(), "MongoConfig","MongoDB Password: " + dotenv.get("MONGO_INITDB_ROOT_PASSWORD"));
        LoggerUtil.info(this.getClass().getSimpleName(), "MongoConfig","MongoDB Database: " + dotenv.get("MONGO_INITDB_DATABASE"));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        String username = dotenv.get("MONGO_INITDB_ROOT_USERNAME");
        String password = dotenv.get("MONGO_INITDB_ROOT_PASSWORD");
        String database = dotenv.get("MONGO_INITDB_DATABASE");
        String uri = "mongodb://" + username + ":" + password + "@localhost:27017/" + database + "?authSource=admin";
        LoggerUtil.info(this.getClass().getSimpleName(), "mongoTemplate", "MongoDB uri: " + uri);
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
    }
}

