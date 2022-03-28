package com.kereq.communicator.common.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractNotificationEvent implements Serializable {

    private static final long serialVersionUID = -6021437682573995913L;

    protected AbstractNotificationEvent(String notificationId, Long senderId) {
        this.notificationId = notificationId;
        this.senderId = senderId;
    }

    protected String notificationId;
    protected Long senderId;
}
