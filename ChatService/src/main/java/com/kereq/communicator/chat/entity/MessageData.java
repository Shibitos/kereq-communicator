package com.kereq.communicator.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "messages")
public class MessageData {

    @Id
    private String id;

    private Long senderId;

    private Long recipientId;

    private String content;

    private String conversationId;

    @CreatedDate
    private Date sendDate;

    private boolean read = false;

    public MessageData(Long senderId, Long recipientId, String content) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }
}
