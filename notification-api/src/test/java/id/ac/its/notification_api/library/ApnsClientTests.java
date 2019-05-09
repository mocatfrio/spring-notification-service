package id.ac.its.notification_api.library;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsPushNotification;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import id.ac.its.notification_api.config.ApnConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApnsClientTests {
    @Autowired
    private ApnsClient client;
    @Autowired
    private ApnConfig config;

    @Test
    public void test_sendNotification_expectSuccess() throws ExecutionException, InterruptedException {
        var notification = buildSampleNotification();
        var future = client.sendNotification(notification);
        var response = future.get();
        Assert.assertTrue(response.isAccepted());
    }

    private ApnsPushNotification buildSampleNotification() {
        var token = "FC57E66B889917A452AE7F72CF0B89074902A4AA719FA3E5CC345C6D2D0434FD";
        var bundleId = config.getBundleId();
        var payload = buildSamplePayload();
        return new SimpleApnsPushNotification(token, bundleId, payload);
    }

    private String buildSamplePayload() {
        return new ApnsPayloadBuilder()
                .setAlertTitle("Sample Title")
                .setAlertBody("Sample Body")
                .buildWithDefaultMaximumLength();
    }
}
