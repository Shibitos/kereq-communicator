package com.kereq.communicator.dispenser.config;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.common.constant.RoutingKey;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue connectionsQueue() {
        return new Queue(QueueName.CONNECTIONS, false);
    }

    @Bean
    DirectExchange connectionsExchange() {
        return new DirectExchange(ExchangeName.CONNECTIONS);
    }

    @Bean
    Binding connectionsBinding(Queue connectionsQueue, DirectExchange connectionsExchange) {
        return BindingBuilder.bind(connectionsQueue).to(connectionsExchange).with(RoutingKey.COMMUNICATOR);
    }
}
