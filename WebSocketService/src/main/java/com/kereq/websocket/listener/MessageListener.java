package com.kereq.websocket.listener;

import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.common.dto.MessageDTO;
import com.kereq.websocket.sender.WebSocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    private final WebSocketSender webSocketSender;

    public MessageListener(WebSocketSender webSocketSender) {
        this.webSocketSender = webSocketSender;
    }

    @RabbitListener(queues = QueueName.MESSAGES_WEBSOCKET)
    public void onMessage(@Payload MessageDTO messageDTO) {
        log.info("Sending WS message (usr: {}, recip: {})", messageDTO.getSenderId(), messageDTO.getRecipientId());
        webSocketSender.sendToUser(messageDTO.getSenderId(), "/queue/messages-inbox", messageDTO);
        webSocketSender.sendToUser(messageDTO.getRecipientId(), "/queue/messages-inbox", messageDTO); //TODO: ?
    }
}
