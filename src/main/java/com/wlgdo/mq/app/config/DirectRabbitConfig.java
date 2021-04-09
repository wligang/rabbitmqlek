package com.wlgdo.mq.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

/**
 *
 **/
@Slf4j
@Configuration
public class DirectRabbitConfig {

    public static final String QUEUE_NAME = "TestDirectQueue";


    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(QUEUE_NAME, true);
    }


    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        //设置连接工厂Bean
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //手动开启
        rabbitTemplate.setMandatory(true);

        //设置传输数据是json格式
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("ConfirmCallback，相关数据：{}", correlationData);
                log.info("ConfirmCallback，确认消息：{}", ack);
                log.info("ConfirmCallback，原因：{}", cause);
            }
        });
//        ReturnCallback：该回调函数的触发器与mandatory: true参数有必要关系
//        流程：交换机-->队列 成功  不触发回调|失败  触发回调

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("ReturnCallback，消息：{}", message);
                log.info("ReturnCallback，回应码：{}", replyCode);
                log.info("ReturnCallback，回应信息：{}", replyText);
                log.info("ReturnCallback，交换机：{}", exchange);
                log.info("ReturnCallback，路由键：{}", routingKey);
            }
        });

        return rabbitTemplate;
    }


    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchange", true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }


    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}