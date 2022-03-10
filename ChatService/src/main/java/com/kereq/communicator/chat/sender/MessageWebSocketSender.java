package com.kereq.communicator.chat.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.dto.MessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageWebSocketSender {

    private final RabbitTemplate rabbitTemplate;

    public MessageWebSocketSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(MessageDTO message) {
        rabbitTemplate.convertAndSend(ExchangeName.MESSAGES_WEBSOCKET, "", message);
    }
}
