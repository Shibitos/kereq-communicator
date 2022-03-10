package com.kereq.communicator.chat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "messages")
public class MessageData {

    @Id
    private String id;

    private Long senderId;

    private Long recipientId;

    private String content;

    @CreatedDate
    private Date sendDate;

    public MessageData(Long senderId, Long recipientId, String content) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }
}
