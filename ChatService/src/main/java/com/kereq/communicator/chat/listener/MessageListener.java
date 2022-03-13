package com.kereq.communicator.chat.listener;

import com.kereq.communicator.chat.entity.ConversationData;
import com.kereq.communicator.chat.entity.MessageData;
import com.kereq.communicator.chat.sender.MessageWebSocketSender;
import com.kereq.communicator.chat.service.ConversationService;
import com.kereq.communicator.chat.service.MessageStorageService;
import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.common.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    private final MessageStorageService messageStorageService;
    private final MessageWebSocketSender messageWebsocketSender;
    private final ConversationService conversationService;

    public MessageListener(MessageStorageService messageStorageService, MessageWebSocketSender messageWebsocketSender, ConversationService conversationService) {
        this.messageStorageService = messageStorageService;
        this.messageWebsocketSender = messageWebsocketSender;
        this.conversationService = conversationService;
    }

    @RabbitListener(queues = QueueName.MESSAGES_CHAT)
    public void onMessage(@Payload MessageDTO messageDTO) {
        MessageData message = new MessageData(messageDTO.getSenderId(), messageDTO.getRecipientId(), messageDTO.getContent());
        message = messageStorageService.storeMessage(message);
        ConversationData conversation = conversationService.updateConversation(message);
        MessageDTO newMessageDTO = new MessageDTO(message.getId(),
                conversation.getId(),
                message.getSenderId(),
                message.getRecipientId(),
                message.getContent(),
                message.getSendDate()); //TODO: modelMapper?
        messageWebsocketSender.send(newMessageDTO);
    }
}
