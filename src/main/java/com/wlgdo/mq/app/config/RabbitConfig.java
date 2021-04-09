//package com.wlgdo.mq.app.config;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.ReturnedMessage;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//
//        // 开启Mandatory, 才能触发回调函数，无论消息推送结果如何都强制调用回调函数
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("ConfirmCallback: " + "相关数据：" + correlationData);
//                System.out.println("ConfirmCallback: " + "确认情况：" + ack);
//                System.out.println("ConfirmCallback: " + "原因：" + cause);
//            }
//        });
//
//        rabbitTemplate.setMandatory(true); // 设置强制标志 仅适用于回退模式
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage(ReturnedMessage returned) {
//                System.out.println(returned.getMessage());
//            }
//        });
//
//        return rabbitTemplate;
//    }
//}