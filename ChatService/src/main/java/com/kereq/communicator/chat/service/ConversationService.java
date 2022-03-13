package com.kereq.communicator.chat.service;

import com.kereq.communicator.chat.entity.ConversationData;
import com.kereq.communicator.chat.entity.MessageData;
import com.kereq.communicator.chat.repository.ConversationRepository;
import com.kereq.communicator.chat.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    private final MessageRepository messageRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    public Page<ConversationData> getLastConversations(long userId, Pageable page) {
        Page<ConversationData> conversations = conversationRepository.findLastConversationsFor(userId, page);
        conversations.forEach(conversation -> conversation.setLastMessage(
                messageRepository.findById(conversation.getLastMessageId()) //TODO: n + 1?
                        .orElseThrow(RuntimeException::new) //TODO: cust excep
        ));
        return conversations;
    }

    public ConversationData updateConversation(MessageData lastMessage) {
        String conversationId = calculateConversationId(lastMessage.getSenderId(), lastMessage.getRecipientId());
        ConversationData conversation = conversationRepository.findById(conversationId)
                .orElse(initializeConversation(conversationId, lastMessage));
        conversation.setLastMessageId(lastMessage.getId());
        conversation.setLastMessageDate(lastMessage.getSendDate());
        if (lastMessage.getSenderId() < lastMessage.getRecipientId()) {
            conversation.setFirstUserMessageCount(conversation.getFirstUserMessageCount() + 1);
        } else {
            conversation.setSecondUserMessageCount(conversation.getSecondUserMessageCount() + 1);
        }
        return conversationRepository.save(conversation);
    }

    private ConversationData initializeConversation(String id, MessageData lastMessage) {
        return new ConversationData(id, lastMessage.getSenderId(), lastMessage.getRecipientId());
    }

    private String calculateConversationId(long senderId, long recipientId) {
        if (senderId == recipientId) {
            throw new IllegalArgumentException(); //TODO: custom
        }
        long calculatedId = (senderId < recipientId) ? oneSideSzudzikFunction(senderId, recipientId) : oneSideSzudzikFunction(recipientId, senderId);
        return String.valueOf(calculatedId);
    }

    /**
     * Calculates unique number value for two given numbers (first must be smaller than second).
     * Simplified version of Szudik's function (a >= b ? a * a + a + b : a + b * b).
     * @param a First operand (must be smaller than second).
     * @param b Second operand (must be bigger than first).
     * @return Unique number representing given pair.
     */
    private static long oneSideSzudzikFunction(long a, long b) {
        return a + b * b;
    }
}
