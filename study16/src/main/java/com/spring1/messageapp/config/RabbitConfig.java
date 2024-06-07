package com.spring1.messageapp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue messageQueue() {
        return new Queue("messageQueue", true);
    }
}
