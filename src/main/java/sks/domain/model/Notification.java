package sks.domain.model;

import lombok.Data;

@Data
public class Notification {
    private final String cleaningUser;
    private final String notificationEmail;
}
