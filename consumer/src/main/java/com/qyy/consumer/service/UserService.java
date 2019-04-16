package com.qyy.consumer.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("PROVIDER")
public interface UserService {

    @RequestMapping(value = "/msg",method = RequestMethod.GET)
    @ResponseBody
    public String  noticeUser();
}
