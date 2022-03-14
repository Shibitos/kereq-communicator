package com.kereq.communicator.chat.repository;

import com.kereq.communicator.chat.entity.MessageData;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    private final MongoTemplate template;

    public MessageRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public boolean markRead(String id, long recipientId) {
        UpdateResult result = template.updateFirst(new Query(Criteria.where("id").is(id).and("recipientId").is(recipientId)),
                new Update().set("read", true), MessageData.class);
        return result.getModifiedCount() > 0;
    }
}
