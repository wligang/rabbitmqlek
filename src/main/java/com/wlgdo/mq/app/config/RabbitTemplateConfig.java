//package com.wlgdo.mq.app.config;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class RabbitTemplateConfig implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setReturnCallback(this);
////        rabbitTemplate.setConfirmCallback(this);
//        //指定 ReturnCallback
//    }
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("消息主体 message : " + message);
//        System.out.println("消息主体 message : " + replyCode);
//        System.out.println("描述：" + replyText);
//        System.out.println("消息使用的交换器 exchange : " + exchange);
//        System.out.println("消息使用的路由键 routing : " + routingKey);
//    }
//
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("消息唯一标识：" + correlationData);
//        System.out.println("确认结果：" + ack);
//        System.out.println("失败原因：" + cause);
//    }
//}