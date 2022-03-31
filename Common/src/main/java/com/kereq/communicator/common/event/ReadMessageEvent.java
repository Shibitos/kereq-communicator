package com.kereq.communicator.common.event;

public class ReadMessageEvent extends AbstractMessageEvent {

    private static final long serialVersionUID = -1842073380390854008L;

    public ReadMessageEvent(String messageId, Long senderId) {
        super(messageId, senderId);
    }
}
