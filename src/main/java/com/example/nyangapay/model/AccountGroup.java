package com.example.nyangapay.model;

import java.util.ArrayList;
import java.util.List;

public class AccountGroup implements INotificationTarget {
    private final String groupName;
    private final List<INotificationTarget> targets = new ArrayList<>();

    public AccountGroup(String groupName) {
        this.groupName = groupName;
    }

    public void addTarget(INotificationTarget target) {
        targets.add(target);
    }

    @Override
    public void receive(String message) {
        System.out.println("--- Début du message pour le groupe [" + groupName + "] ---");
        for (INotificationTarget target : targets) {
            target.receive(message);
        }
        System.out.println("--- Fin du message pour le groupe [" + groupName + "] ---");
    }
}
