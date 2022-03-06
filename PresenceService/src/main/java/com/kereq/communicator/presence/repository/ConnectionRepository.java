package com.kereq.communicator.presence.repository;

import com.kereq.communicator.presence.entity.ConnectionData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConnectionRepository extends MongoRepository<ConnectionData, String> {

    boolean existsByUserIdAndInstanceId(long userId, String instanceId);

    boolean existsByUserId(long userId);

    ConnectionData findByUserIdAndInstanceId(long userId, String instanceId);
}
