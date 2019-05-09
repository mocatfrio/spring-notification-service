package id.ac.its.notification_api.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "application.fcm")
@Setter
@Getter
public class FcmConfig {
    private Resource credentials;

    @Bean
    protected FirebaseMessaging buildFirebaseMessaging() throws IOException {
        var googleCredentials = GoogleCredentials.fromStream(credentials.getInputStream());
        var options = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        var app = FirebaseApp.initializeApp(options);
        return FirebaseMessaging.getInstance(app);
    }
}
