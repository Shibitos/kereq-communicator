package com.kereq.communicator.notification.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "notifications")
public class NotificationData {

    @Id
    private String id;

    private Long sourceUserId;

    private Long recipientId;

    private String title;

    private String content;

    private Date date;

    private boolean read = false;

    public NotificationData(Long sourceUserId, Long recipientId, String title, String content, Date date) {
        this.sourceUserId = sourceUserId;
        this.recipientId = recipientId;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
