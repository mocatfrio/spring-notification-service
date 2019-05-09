package id.ac.its.notification_api.library;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirebaseMessagingTests {
    @Autowired
    private FirebaseMessaging messaging;

    @Test
    public void test_sendNotification_expectSuccess() throws ExecutionException, InterruptedException {
        var message = buildSampleMessage();
        var future = messaging.sendAsync(message);
        var messageId = future.get();
        Assert.assertNotNull(messageId);
    }

    private Message buildSampleMessage() {
        var token = "d1PwrfERxj0:APA91bH8IQFvewOYDSEssqvGh8G_421H_KupT5HY4XX9D__VkAQ-hoHq_1ZJxqC64VYaCHho96NYORCh2i_0-c6S2Ycemx8T8e6mPiCpZdmmg3HMcv3QH285JjGp7WUiOtRXHw_5l7Kc";
        var notification = new Notification("Sample Title", "Sample Body");
        return Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();
    }
}
