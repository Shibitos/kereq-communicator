package com.kereq.communicator.chat.repository;

public interface MessageRepositoryCustom {

    boolean markRead(String id, long recipientId);
}
