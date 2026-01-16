package com.example.nyangapay.service;

import com.example.nyangapay.model.INotificationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final List<ISmsProvider> smsProviders;
    private final NotificationServiceManager globalManager;

    @Autowired
    public NotificationService(List<ISmsProvider> smsProviders, NotificationServiceManager globalManager) {
        this.smsProviders = smsProviders;
        this.globalManager = globalManager;
    }

    public void notify(INotificationTarget target, String message) {
        target.receive(message);
        globalManager.sendNotification(message);
        
        // Use the first available SMS provider as a default adapter usage example
        if (!smsProviders.isEmpty()) {
            smsProviders.get(0).sendSms("000000000", message);
        }
    }
}
