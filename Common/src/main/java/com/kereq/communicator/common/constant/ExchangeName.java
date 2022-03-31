package com.kereq.communicator.common.constant;

public class ExchangeName {

    private ExchangeName() {
    }

    public static final String CONNECTIONS_PRESENCE = "exchange.presence.connections";
    public static final String CONNECTIONS_BACKEND = "exchange.backend.connections";
    public static final String CONNECTIONS_WEBSOCKET = "exchange.websocket.connections";

    public static final String MESSAGES_CHAT = "exchange.chat.messages";
    public static final String MESSAGES_WEBSOCKET = "exchange.websocket.messages";

    public static final String NOTIFICATIONS_NOTIFICATION = "exchange.notification.notifications";
    public static final String NOTIFICATIONS_WEBSOCKET = "exchange.websocket.notifications";

    public static final String EVENTS_MESSAGE_CHAT = "exchange.chat.events.message";
    public static final String EVENTS_NOTIFICATION_NOTIFICATION = "exchange.notification.events.notification";
}
