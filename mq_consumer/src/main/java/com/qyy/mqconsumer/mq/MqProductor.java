package com.qyy.mqconsumer.mq;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqProductor implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {


    private static final String exchange = "fanout_exchange";
    private static final String queue = "queue_1";

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MqProductor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

        if (!b) {
            System.out.println("消息发送失败, 失败内容为:" + correlationData.getId());
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("发送消息到队列失败，响应编码" + i + ";响应内容:" + s + "失败信息: 【 " + message + " 】");
    }

    public void send(String s){
        rabbitTemplate.convertAndSend(exchange,queue,s);
    }
}
