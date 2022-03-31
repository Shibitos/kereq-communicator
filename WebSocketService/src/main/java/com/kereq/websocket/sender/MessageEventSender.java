package com.kereq.websocket.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.event.AbstractMessageEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageEventSender {

    private final RabbitTemplate rabbitTemplate;

    public MessageEventSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(AbstractMessageEvent messageEvent) {
        rabbitTemplate.convertAndSend(ExchangeName.EVENTS_MESSAGE_CHAT, "", messageEvent);
    }
}
