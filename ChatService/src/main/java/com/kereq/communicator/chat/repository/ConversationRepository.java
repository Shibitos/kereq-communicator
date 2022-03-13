package com.kereq.communicator.chat.repository;

import com.kereq.communicator.chat.entity.ConversationData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConversationRepository extends MongoRepository<ConversationData, String> {

    @Query("{ '$or': [ {'firstUserId': :#{#userId} }, {'secondUserId': :#{#userId} } ] }")
    Page<ConversationData> findLastConversationsFor(@Param("userId") long userId, Pageable page);
}
