package id.ac.its.notification_api.service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import id.ac.its.notification_api.dao.DeviceDao;
import id.ac.its.notification_api.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class FcmService {
    private static final Logger LOG = LoggerFactory.getLogger(FcmService.class);
    private final Executor executor = Executors.newCachedThreadPool();
    private final FirebaseMessaging messaging;
    private final DeviceDao deviceDao;

    public FcmService(FirebaseMessaging messaging, DeviceDao deviceDao) {
        this.messaging = messaging;
        this.deviceDao = deviceDao;
    }

    public void sendNotification(Integer userId, String title, String body) {
        var devices = deviceDao.getByUserId(userId);
        if (devices.isEmpty()) return;

        var tokens = devices.stream()
                .filter(Device::isFcm)
                .map(Device::getToken)
                .distinct()
                .collect(Collectors.toUnmodifiableList());

        var notification = new Notification(title, body);
        var message = MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(tokens)
                .build();

        var future = messaging.sendMulticastAsync(message);
        var listener = buildListener(future);
        future.addListener(listener, executor);
    }

    private Runnable buildListener(ApiFuture<BatchResponse> future) {
        return () -> {
            try {
                var responses = future.get().getResponses();
                responses.forEach(response -> {
                    if (response.isSuccessful()) LOG.info("success: {}.", response.getMessageId());
                    else LOG.error(response.getException().getErrorCode(), response.getException());
                });
            } catch (InterruptedException | ExecutionException e) {
                LOG.error(e.getMessage(), e);
            }
        };
    }
}
