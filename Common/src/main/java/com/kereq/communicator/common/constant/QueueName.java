package com.kereq.communicator.common.constant;

public class QueueName {

    private QueueName() {
    }

    public static final String CONNECTIONS_PRESENCE = "queue.presence.connections";
    public static final String CONNECTIONS_WEBSOCKET = "queue.websocket.connections"; //TODO: change to events

    public static final String MESSAGES_CHAT = "queue.chat.messages";
    public static final String MESSAGES_WEBSOCKET = "queue.websocket.messages";

    public static final String EVENTS_MESSAGE_CHAT = "queue.chat.events.message";
}
