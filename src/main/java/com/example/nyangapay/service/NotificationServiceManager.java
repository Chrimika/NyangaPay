package com.example.nyangapay.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationServiceManager {
    public NotificationServiceManager() {
        System.out.println("Initialisation du gestionnaire de notifications (Spring Bean)");
    }

    public void sendNotification(String message) {
        System.out.println("Envoi global : " + message);
    }
}
