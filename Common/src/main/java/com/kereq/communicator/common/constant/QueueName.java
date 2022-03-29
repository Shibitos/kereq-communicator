package com.kereq.communicator.common.constant;

public class QueueName {

    private QueueName() {
    }

    public static final String CONNECTIONS_PRESENCE = "queue.presence.connections";
    public static final String CONNECTIONS_WEBSOCKET = "queue.websocket.connections"; //TODO: change to events

    public static final String MESSAGES_CHAT = "queue.chat.messages";
    public static final String MESSAGES_WEBSOCKET = "queue.websocket.messages";

    public static final String NOTIFICATIONS_NOTIFICATION = "queue.notification.notifications";
    public static final String NOTIFICATIONS_WEBSOCKET = "queue.websocket.notifications";

    public static final String EVENTS_MESSAGE_CHAT = "queue.chat.events.message";
    public static final String EVENTS_NOTIFICATION_NOTIFICATION = "queue.notification.events.notification";
}
