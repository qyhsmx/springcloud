package com.qyy.consumer.service;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("PROVIDER")
public interface UserService {

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    @ResponseBody
    @LoadBalanced
    public String noticeUser();
}
