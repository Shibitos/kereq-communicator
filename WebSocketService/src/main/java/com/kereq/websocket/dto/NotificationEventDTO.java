package com.kereq.websocket.dto;

import com.kereq.communicator.common.event.AbstractNotificationEvent;
import com.kereq.communicator.common.event.ReadNotificationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEventDTO implements Serializable {

    private static final long serialVersionUID = 5155244805405000395L;

    @AllArgsConstructor
    enum NotificationEventType {
        READ("READ");

        String code;
    }

    String notificationId;

    NotificationEventType type;

    public AbstractNotificationEvent toNotificationEvent(Long senderId) {
        switch (type) {
            case READ:
                return new ReadNotificationEvent(notificationId, senderId);
            default:
                throw new IllegalArgumentException(); //TODO: except
        }
    }
}
