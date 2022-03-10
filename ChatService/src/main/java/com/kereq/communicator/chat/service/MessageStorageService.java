package com.kereq.communicator.chat.service;

import com.kereq.communicator.chat.entity.MessageData;
import com.kereq.communicator.chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageStorageService {

    private final MessageRepository messageRepository;

    public MessageStorageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageData storeMessage(MessageData message) {
        return messageRepository.save(message);
    }
}
