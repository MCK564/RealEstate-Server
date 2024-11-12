package org.example.listingservice.services.rabbitMQ;

import org.example.listingservice.configurations.RabbitMQConfiguration;
import org.example.listingservice.dtos.RegisterDTO;
import org.example.listingservice.messages.AvatarMessage;
import org.example.listingservice.messages.PaymentMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate template){rabbitTemplate = template;}

    public  void sendMessage(String exchange, String routingKey, AvatarMessage avatarMessage){
        rabbitTemplate.convertAndSend(exchange,routingKey, avatarMessage);
    }


    public void sendMailToQueue(PaymentMessage payment){
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.EMAIL_QUEUE,payment);
    }

    public void uploadImage(AvatarMessage message){
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.UPLOADS_QUEUE,message);
    }

    public void sendMailToQueue(RegisterDTO dto){
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.EMAIL_REGISTER_QUEUE,dto);
    }

}
