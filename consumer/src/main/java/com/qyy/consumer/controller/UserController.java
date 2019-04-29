package com.qyy.consumer.controller;

import com.qyy.consumer.service.HystrixService;
import com.qyy.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private HystrixService service;

    @GetMapping("/do.action")
    public String send() {

        /*String object = restTemplate.getForObject("http://PROVIDER/msg", String.class);
        return  object;*/

        String user = userService.noticeUser();

        return user;
    }

    @GetMapping("/hystrix.action")
    public String hystrix() {
        return service.sayHi();
    }


}
