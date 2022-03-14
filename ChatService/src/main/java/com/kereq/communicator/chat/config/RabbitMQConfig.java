package com.kereq.communicator.chat.config;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.constant.QueueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue messagesQueue() {
        return new Queue(QueueName.MESSAGES_CHAT, false);
    }

    @Bean
    FanoutExchange messagesExchange() {
        return new FanoutExchange(ExchangeName.MESSAGES_CHAT);
    }

    @Bean
    Binding messagesBinding(Queue messagesQueue, FanoutExchange messagesExchange) {
        return BindingBuilder.bind(messagesQueue).to(messagesExchange);
    }

    @Bean
    Queue eventsMessageQueue() {
        return new Queue(QueueName.EVENTS_MESSAGE_CHAT, false);
    }

    @Bean
    FanoutExchange eventsMessageExchange() {
        return new FanoutExchange(ExchangeName.EVENTS_MESSAGE_CHAT);
    }

    @Bean
    Binding eventsMessageBinding(Queue eventsMessageQueue, FanoutExchange eventsMessageExchange) {
        return BindingBuilder.bind(eventsMessageQueue).to(eventsMessageExchange);
    }
}
