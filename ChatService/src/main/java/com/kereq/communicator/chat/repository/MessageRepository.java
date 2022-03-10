package com.kereq.communicator.chat.repository;

import com.kereq.communicator.chat.entity.MessageData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageData, String> {
}
