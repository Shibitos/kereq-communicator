package com.kereq.communicator.dispenser.repository;

import com.kereq.communicator.dispenser.entity.ConnectionData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConnectionRepository extends MongoRepository<ConnectionData, String> {

    boolean existsByUserIdAndInstanceId(long userId, String instanceId);

    ConnectionData findByUserIdAndInstanceId(long userId, String instanceId);
}
