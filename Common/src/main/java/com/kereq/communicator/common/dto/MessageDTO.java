package com.kereq.communicator.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 7334460075840016146L;

    private String id;

    private String conversationId;

    private Long senderId;

    private Long recipientId;

    private String content;

    private Date sendDate;

    private boolean read;

    public MessageDTO(String id, String conversationId, Long senderId, Long recipientId, String content, Date sendDate, boolean read) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.sendDate = sendDate;
        this.read = read;
    }

    public MessageDTO(Long senderId, Long recipientId, String content) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO that = (MessageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(conversationId, that.conversationId) && Objects.equals(senderId, that.senderId) && Objects.equals(recipientId, that.recipientId) && Objects.equals(content, that.content) && Objects.equals(sendDate, that.sendDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conversationId, senderId, recipientId, content, sendDate);
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id='" + id + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", content='" + content + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
