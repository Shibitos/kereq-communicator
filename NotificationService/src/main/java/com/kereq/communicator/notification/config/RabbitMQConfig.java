package com.kereq.communicator.notification.config;

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
    Queue notificationsQueue() {
        return new Queue(QueueName.NOTIFICATIONS_NOTIFICATION, false);
    }

    @Bean
    FanoutExchange notificationsExchange() {
        return new FanoutExchange(ExchangeName.NOTIFICATIONS_NOTIFICATION);
    }

    @Bean
    Binding notificationsBinding(Queue notificationsQueue, FanoutExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsQueue).to(notificationsExchange);
    }

    @Bean
    Queue eventsNotificationQueue() {
        return new Queue(QueueName.EVENTS_NOTIFICATION_NOTIFICATION, false);
    }

    @Bean
    FanoutExchange eventsNotificationExchange() {
        return new FanoutExchange(ExchangeName.EVENTS_NOTIFICATION_NOTIFICATION);
    }

    @Bean
    Binding eventsNotificationBinding(Queue eventsNotificationQueue, FanoutExchange eventsNotificationExchange) {
        return BindingBuilder.bind(eventsNotificationQueue).to(eventsNotificationExchange);
    }
}
