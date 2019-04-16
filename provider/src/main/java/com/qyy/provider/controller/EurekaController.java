package com.qyy.provider.controller;


import com.qyy.provider.service.EurekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaController {

    @Autowired
    private EurekaService eurekaService;
    @GetMapping("/msg")
    public String getMsg(){
        return eurekaService.msg();
    }
}
