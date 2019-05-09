package id.ac.its.notification_api.controller;

import id.ac.its.notification_api.service.ApnService;
import id.ac.its.notification_api.service.FcmService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final ApnService apnService;
    private final FcmService fcmService;

    public NotificationController(ApnService apnService, FcmService fcmService) {
        this.apnService = apnService;
        this.fcmService = fcmService;
    }

    @PostMapping("/account/{accountId}")
    public void sendNotificationToAccount(@PathVariable UUID accountId,
                                          @RequestParam String title,
                                          @RequestParam String body) {
        apnService.sendNotification(accountId, title, body);
        fcmService.sendNotification(accountId, title, body);
    }
}
