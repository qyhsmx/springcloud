package com.qyy.provider.service;


import org.springframework.stereotype.Service;

@Service
public class EurekaService {

    public String msg(){
        return  "hello spring cloud with eureka";
    }
}
