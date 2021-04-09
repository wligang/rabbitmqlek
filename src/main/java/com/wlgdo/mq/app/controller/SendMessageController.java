package com.wlgdo.mq.app.controller;

import com.wlgdo.mq.app.esUserService.IUserService;
import com.wlgdo.mq.app.model.Users;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Autowired
    private IUserService userService;


    @GetMapping("/user")
    public Users getUserInfo(@RequestParam(required = false, name = "userId") String userId) {
        return userService.query(userId);

    }

    @GetMapping("/user1")
    public Users getUserInfo1(@RequestParam(required = false, name = "userId") String userId) {
        Users users = new Users(userId, "name-" + userId, "18");
        return userService.save(users);

    }


    @GetMapping("/send")
    public String sendDirectMessage(@RequestParam(required = false, name = "msg") String msg) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!" + msg;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/send1")
    public String sendDirectMessage1(@RequestParam(required = false, name = "msg") String msg) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!" + msg;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting1", map);
        return "ok";
    }

}