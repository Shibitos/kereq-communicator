package com.kereq.websocket.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.dto.MessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageChatSender {

    private final RabbitTemplate rabbitTemplate;

    public MessageChatSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(MessageDTO message) {
        rabbitTemplate.convertAndSend(ExchangeName.MESSAGES_CHAT, "", message);
    }
}
