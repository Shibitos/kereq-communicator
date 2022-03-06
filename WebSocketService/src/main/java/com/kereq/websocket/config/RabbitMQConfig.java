package com.kereq.websocket.config;

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
}
