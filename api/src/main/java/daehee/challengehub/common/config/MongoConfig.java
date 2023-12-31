package daehee.challengehub.common.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    private final Environment env;
    private final StandardPBEStringEncryptor encryptor;

    @Autowired
    public MongoConfig(Environment env, StandardPBEStringEncryptor encryptor) {
        this.env = env;
        this.encryptor = encryptor;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        String username = encryptor.decrypt(env.getProperty("MONGO_INITDB_ROOT_USERNAME"));
        String password = encryptor.decrypt(env.getProperty("MONGO_INITDB_ROOT_PASSWORD"));
        String uri = "mongodb://" + username + ":" + password + "@localhost:27017/";
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
    }
}

