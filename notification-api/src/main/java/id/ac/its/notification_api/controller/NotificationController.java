package id.ac.its.notification_api.controller;

import id.ac.its.notification_api.service.ApnService;
import id.ac.its.notification_api.service.FcmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final ApnService apnService;
    private final FcmService fcmService;

    public NotificationController(ApnService apnService, FcmService fcmService) {
        this.apnService = apnService;
        this.fcmService = fcmService;
    }

    @PostMapping("/user")
    public void sendToUser(@RequestParam List<Integer> userIds,
                           @RequestParam String title,
                           @RequestParam String body) {
        userIds.forEach(userId -> {
            apnService.sendNotification(userId, title, body);
            fcmService.sendNotification(userId, title, body);
        });
    }
}
