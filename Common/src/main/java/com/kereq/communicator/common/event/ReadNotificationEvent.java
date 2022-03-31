package com.kereq.communicator.common.event;

public class ReadNotificationEvent extends AbstractNotificationEvent {

    private static final long serialVersionUID = -286224593148092776L;

    public ReadNotificationEvent(String messageId, Long senderId) {
        super(messageId, senderId);
    }
}
