package com.wy.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "boot_topics_exchange";
    public static final String QUEUE_NAME = "boot_queue";
    @Bean("exchange")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
    @Bean("queue")
    public Queue queue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    @Bean
    public Binding bindingQueueExchange(@Qualifier("queue") Queue queue,@Qualifier("exchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.*").noargs();
    }
}
