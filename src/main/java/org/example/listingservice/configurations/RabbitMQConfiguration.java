package org.example.listingservice.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String EMAIL_QUEUE = "emailQueue";
    public static final String UPLOADS_QUEUE = "uploadsQueue";
    public static final String EMAIL_REGISTER_QUEUE = "registerEmailQueue";

    @Bean
    public Queue emailQueue(){
        return new Queue(EMAIL_QUEUE, false);
    }

    @Bean
    public Queue uploadsQueue(){
        return new Queue(UPLOADS_QUEUE,false);
    }

    @Bean
    public Queue registerEmailQueue(){return new Queue(EMAIL_REGISTER_QUEUE,false);}

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}


//    docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
//    docker stop b58cc1efc876bcea68217cfd38f4424cdb7051428543925a00b84734472be82e
//    docker start b58cc1efc876bcea68217cfd38f4424cdb7051428543925a00b84734472be82e
