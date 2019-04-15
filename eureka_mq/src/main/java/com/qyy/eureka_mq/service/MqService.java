package com.qyy.eureka_mq.service;


import com.qyy.eureka_mq.entity.MqInterface;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class MqService {


    public void toxml(MqInterface data){

        XStream xStream = new XStream();


        //让注解生效
        xStream.processAnnotations(MqInterface.class);

        String toXML = xStream.toXML(data);

        System.out.println("转化成的xml文件是********"+toXML);

        try {
            //注册转换器
            xStream.registerConverter(new NullConverter());

            PrintWriter writer = new PrintWriter("D:\\文件-mqInterface.xml", "utf-8");

            xStream.marshal(data, new CompactWriter(writer));
        }catch (IOException e){
            System.out.println("数据写入错误");
        }








    }

}
