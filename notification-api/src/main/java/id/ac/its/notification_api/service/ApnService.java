package id.ac.its.notification_api.service;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;
import id.ac.its.notification_api.config.ApnConfig;
import id.ac.its.notification_api.dao.DeviceDao;
import id.ac.its.notification_api.model.Device;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ApnService {
    private static final Logger LOG = LoggerFactory.getLogger(ApnService.class);
    private final ApnsClient client;
    private final String bundleId;
    private final DeviceDao deviceDao;

    public ApnService(ApnsClient client, ApnConfig config, DeviceDao deviceDao) {
        this.client = client;
        this.bundleId = config.getBundleId();
        this.deviceDao = deviceDao;
    }

    public void sendNotification(Integer userId, String title, String body) {
        var devices = deviceDao.findByUserIdAndTypeIn(userId, Device.Type.IOS);
        if (devices.isEmpty()) return;

        var payload = new ApnsPayloadBuilder()
                .setAlertTitle(title)
                .setAlertBody(body)
                .buildWithDefaultMaximumLength();

        var futures = devices.stream()
                .map(Device::getToken)
                .distinct()
                .map(token -> new SimpleApnsPushNotification(token, bundleId, payload))
                .map(client::sendNotification)
                .collect(Collectors.toList());

        var listener = buildListener();
        futures.forEach(future -> future.addListener(listener));
    }

    private GenericFutureListener<PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>> buildListener() {
        return (future -> {
            if (future.isSuccess()) {
                var response = future.get();
                if (response.isAccepted()) LOG.info("success: {}.", response.getApnsId());
                else LOG.error("rejected: {}.", response.getRejectionReason());
            } else LOG.error(future.cause().getMessage(), future.cause());
        });
    }
}
