package com.kereq.websocket.dto;

import com.kereq.communicator.common.event.AbstractMessageEvent;
import com.kereq.communicator.common.event.ReadMessageEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageEventDTO implements Serializable {

    private static final long serialVersionUID = 6729117608501482926L;

    @AllArgsConstructor
    enum MessageEventType {
        READ("READ");

        String code;
    }

    String messageId;

    MessageEventType type;

    public AbstractMessageEvent toMessageEvent(Long senderId) {
        switch (type) {
            case READ:
                return new ReadMessageEvent(messageId, senderId);
            default:
                throw new IllegalArgumentException(); //TODO: except
        }
    }
}
