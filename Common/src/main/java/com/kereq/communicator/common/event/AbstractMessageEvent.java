package com.kereq.communicator.common.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractMessageEvent implements Serializable {

    private static final long serialVersionUID = 3304473378001102303L;

    protected AbstractMessageEvent(String messageId, Long senderId) {
        this.messageId = messageId;
        this.senderId = senderId;
    }

    String messageId;
    Long senderId;
}
