package id.ac.its.notification_api.config;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "application.apn")
@Setter
@Getter
public class ApnConfig {
    private Resource certificate;
    private String password;
    private String bundleId;

    @Bean
    protected ApnsClient buildApnsClient() throws IOException {
        return new ApnsClientBuilder()
                .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
                .setClientCredentials(certificate.getInputStream(), password)
                .build();
    }
}
