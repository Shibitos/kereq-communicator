package com.kereq.communicator.chat.listener;

import com.kereq.communicator.chat.service.MessageStorageService;
import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.common.event.AbstractMessageEvent;
import com.kereq.communicator.common.event.ReadMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageEventListener {

    private final MessageStorageService messageStorageService;

    public MessageEventListener(MessageStorageService messageStorageService) {
        this.messageStorageService = messageStorageService;
    }

    @RabbitListener(queues = QueueName.EVENTS_MESSAGE_CHAT)
    public void onMessage(@Payload AbstractMessageEvent messageEvent) {
        if (messageEvent instanceof ReadMessageEvent) { //TODO: handlers
            if (!messageStorageService.markMessageRead(messageEvent.getMessageId(), messageEvent.getSenderId())) {
                log.error("Error while marking message {} read", messageEvent.getMessageId());
            }
        }
    }
}
