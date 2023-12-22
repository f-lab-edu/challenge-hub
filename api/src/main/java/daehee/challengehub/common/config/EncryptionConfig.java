package daehee.challengehub.common.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class EncryptionConfig {
    @Bean
    public StandardPBEStringEncryptor stringEncryptor(Environment env) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(Objects.requireNonNull(env.getProperty("DECRYPTION_KEY"))); // 이 값은 외부에서 설정해야 합니다.
        return encryptor;
    }
}
