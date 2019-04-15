package com.qyy.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/do.action")
    public String send(){

        String object = restTemplate.getForObject("http://PROVIDER/msg", String.class);
        return  object;
    }


}
