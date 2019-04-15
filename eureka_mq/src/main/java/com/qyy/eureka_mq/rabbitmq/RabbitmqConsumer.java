package com.qyy.eureka_mq.rabbitmq;


import com.google.gson.Gson;
import com.qyy.eureka_mq.entity.MqInterface;
import com.qyy.eureka_mq.service.MqService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
//@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitmqConsumer {


    private Gson gson = new Gson();

    @Autowired
    private MqService service;

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static final String exchange = "fanout_exchange";
    private static final String queue = "queue_1";
    private static final String routingKey = "";


    public void processData(String data){

        MqInterface json = gson.fromJson(data, MqInterface.class);

        //保存为xml
        service.toxml(json);


    }


    /**
     * 设置mq缓存连接工厂
     * @return
     */
    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(addresses);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPublisherConfirms(true);
        return factory;
    }

    /**
     * mq的模板类 必须是多实例
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(this.cachingConnectionFactory());
        return template;
    }

    /**
     * 交换器
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(exchange,true,false);
    }

    /**
     * 创建消息队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(queue,true);
    }


    /**
     * fanout不用绑定routingkey
     * 返回binding对象
     * direct需绑定
     * 返回BindingBuilder.DirectExchangeRoutingKeyConfigurer对象，有with方法
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(this.queue()).to(this.fanoutExchange());
    }

    /**
     * 接受数据的容器
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(this.cachingConnectionFactory());

        container.setQueues(this.queue());
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setConcurrentConsumers(20);//最小消费者实例数
        container.setMaxConcurrentConsumers(300);//最大消费者实例数
        container.setPrefetchCount(300);//一次批处理多少

        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String s = new String(body);
                //处理数据
                System.out.println("mq接受的数据是*******"+s);
                processData(s);
            }
        });

        return container;
    }



}
