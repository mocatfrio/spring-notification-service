package id.ac.its.notification_api.controller;

import id.ac.its.notification_api.service.ApnService;
import id.ac.its.notification_api.service.FcmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @PostMapping("/account")
    public void sendToAccounts(@RequestParam("accounts") List<UUID> accountIds,
                               @RequestParam String title,
                               @RequestParam String body) {
        accountIds.forEach(accountId -> {
            apnService.sendNotification(accountId, title, body);
            fcmService.sendNotification(accountId, title, body);
        });
    }
}
