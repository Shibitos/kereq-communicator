package com.kereq.communicator.notification.repository;

public interface NotificationRepositoryCustom {

    boolean markRead(String id, long recipientId);
}
