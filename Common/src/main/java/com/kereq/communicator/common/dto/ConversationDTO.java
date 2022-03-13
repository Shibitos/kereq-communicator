package com.kereq.communicator.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class ConversationDTO implements Serializable {

    private static final long serialVersionUID = 4569257124209264118L;

    private String id;

    private Long firstUserId;

    private Long secondUserId;

    private MessageDTO lastMessage;

    private Long firstUserMessageCount;

    private Long secondUserMessageCount;

    public ConversationDTO(String id, Long firstUserId, Long secondUserId, MessageDTO lastMessage, Long firstUserMessageCount, Long secondUserMessageCount) {
        this.id = id;
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.lastMessage = lastMessage;
        this.firstUserMessageCount = firstUserMessageCount;
        this.secondUserMessageCount = secondUserMessageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationDTO that = (ConversationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstUserId, that.firstUserId) && Objects.equals(secondUserId, that.secondUserId) && Objects.equals(firstUserMessageCount, that.firstUserMessageCount) && Objects.equals(secondUserMessageCount, that.secondUserMessageCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstUserId, secondUserId, firstUserMessageCount, secondUserMessageCount);
    }

    @Override
    public String toString() {
        return "ConversationDTO{" +
                "id='" + id + '\'' +
                ", firstUserId=" + firstUserId +
                ", secondUserId=" + secondUserId +
                ", lastMessage=" + lastMessage +
                ", firstUserMessageCount=" + firstUserMessageCount +
                ", secondUserMessageCount=" + secondUserMessageCount +
                '}';
    }
}
