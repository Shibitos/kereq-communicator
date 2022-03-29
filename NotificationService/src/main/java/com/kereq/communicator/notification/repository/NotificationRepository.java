package com.kereq.communicator.notification.repository;

import com.kereq.communicator.notification.entity.NotificationData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<NotificationData, String>, NotificationRepositoryCustom {

    Page<NotificationData> findByRecipientId(@Param("recipientId") long recipientId, Pageable page);

    boolean existsByUuid(UUID uuid);
}
