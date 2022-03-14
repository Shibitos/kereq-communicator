package com.kereq.communicator.chat.repository;

import com.kereq.communicator.chat.entity.MessageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends MongoRepository<MessageData, String>, MessageRepositoryCustom {

    @Query("{ '$or': [ {'senderId': :#{#userId} , 'recipientId': :#{#recipientId} }, " +
            "{'senderId': :#{#recipientId} , 'recipientId': :#{#userId} } ] }")
    Page<MessageData> findMessagesFor(@Param("userId") long userId, @Param("recipientId") long recipientId, Pageable page);
}
