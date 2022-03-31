package com.kereq.communicator.chat.service;

import com.kereq.communicator.chat.entity.MessageData;
import com.kereq.communicator.chat.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageStorageService {

    private final MessageRepository messageRepository;

    public MessageStorageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageData storeMessage(MessageData message) {
        //TODO: null check content (ws too?), other validation?
        return messageRepository.save(message);
    }

    public boolean markMessageRead(String messageId, long recipientId) {
        return messageRepository.markRead(messageId, recipientId);
    }

    public Page<MessageData> getMessagesFor(long userId, long recipientId, Pageable page) {
        return messageRepository.findMessagesFor(userId, recipientId, page);
    }
}
