package com.code_challenge.bank.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String TRANSACTIONS_QUEUE = "transactions-queue";
    public static final String TRANSACTIONS_EXCHANGE = "transactions-exchange";
    public static final String TRANSACTIONS_ROUTING_KEY = "transaction";

    public static final String NOTIFICATIONS_QUEUE = "notifications-queue";
    public static final String NOTIFICATIONS_EXCHANGE = "notifications-exchange";
    public static final String NOTIFICATIONS_ROUTING_KEY = "notification";

    @Bean
    public Queue transactionsQueue() {
        return new Queue(TRANSACTIONS_QUEUE);
    }

    @Bean
    public DirectExchange transactionsExchange() {
        return new DirectExchange(TRANSACTIONS_EXCHANGE);
    }

    @Bean
    public Binding transactionsBinding(Queue transactionsQueue, DirectExchange transactionsExchange) {
        return BindingBuilder.bind(transactionsQueue).to(transactionsExchange).with(TRANSACTIONS_ROUTING_KEY);
    }

    @Bean
    public Queue notificationsQueue() {
        return new Queue(NOTIFICATIONS_QUEUE);
    }

    @Bean
    public DirectExchange notificationsExchange() {
        return new DirectExchange(NOTIFICATIONS_EXCHANGE);
    }

    @Bean
    public Binding notificationsBinding(Queue transactionsQueue, DirectExchange transactionsExchange) {
        return BindingBuilder.bind(transactionsQueue).to(transactionsExchange).with(NOTIFICATIONS_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
