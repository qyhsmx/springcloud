package com.qyy.mqconsumer;

import com.google.gson.Gson;
import com.qyy.mqconsumer.entity.MqInterface;
import com.qyy.mqconsumer.mq.MqProductor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqconsumerApplicationTests {


    private Gson gson = new Gson();
    @Autowired
    private MqProductor productor;
    @Test
    public void contextLoads() {

        MqInterface mqInterface = new MqInterface();
        mqInterface.setUsername("秦勇");
        mqInterface.setPassword("123");

        String json = gson.toJson(mqInterface);


        productor.send(json);
    }

}
