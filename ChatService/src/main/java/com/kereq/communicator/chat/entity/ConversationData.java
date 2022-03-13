package com.kereq.communicator.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "conversations")
public class ConversationData {

    @Id
    private String id;

    private Long firstUserId;

    private Long secondUserId;

    private String lastMessageId;

    private Date lastMessageDate;

    private Long firstUserMessageCount;

    private Long secondUserMessageCount;

    @Transient
    private MessageData lastMessage;

    public ConversationData(String id, long firstUserId, long secondUserId) {
        if (firstUserId == secondUserId) {
            throw new IllegalArgumentException("Given ids cannot be same");
        }
        this.id = id;
        this.firstUserId = Math.min(firstUserId, secondUserId);
        this.secondUserId = Math.max(firstUserId, secondUserId);
        this.firstUserMessageCount = 0L;
        this.secondUserMessageCount = 0L;
    }
}
