package com.kereq.websocket.config;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.constant.QueueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String instanceId;

    public RabbitMQConfig(@Value("${eureka.instance.metadataMap.instanceId}") String instanceId) {
        this.instanceId = instanceId;
    }

    @Bean
    Queue connectionsQueue() {
        return new Queue(QueueName.CONNECTIONS_WEBSOCKET, false);
    }

    @Bean
    FanoutExchange connectionsExchange() {
        return new FanoutExchange(ExchangeName.CONNECTIONS_WEBSOCKET);
    }

    @Bean
    Binding connectionsBinding(Queue connectionsQueue, FanoutExchange connectionsExchange) {
        return BindingBuilder.bind(connectionsQueue).to(connectionsExchange);
    }
    
    @Bean
    Queue messagesQueue() {
        return new Queue(QueueName.MESSAGES_WEBSOCKET, false);
    }

    @Bean
    FanoutExchange messagesExchange() {
        return new FanoutExchange(ExchangeName.MESSAGES_WEBSOCKET);
    }

    @Bean
    Binding messagesBinding(Queue messagesQueue, FanoutExchange messagesExchange) {
        return BindingBuilder.bind(messagesQueue).to(messagesExchange);
    }
}
