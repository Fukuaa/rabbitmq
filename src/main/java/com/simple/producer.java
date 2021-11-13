package com.simple;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class producer {
    public static void main(String[] args) throws IOException, TimeoutException {
       /* String EXCHANGE_NAME = "exchange_demo";
        String ROUTING_KEY = "routingkey_demo";
        String QUEUE_NAME = "queue_demo";
        String IP_ADDRESS = "47.113.199.238";
        int PORT = 5672;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("admin");
        factory.setPassword("admin");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建一个type="direct"、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        //发送一条持久化的消息：hello world！
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        //关闭资源
        channel.close();
        connection.close();*/
        //创建连接工程
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.113.199.238");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            //创建连接connection
            connection = factory.newConnection("生成者");
            //通过连接获取通道channel
            channel = connection.createChannel();
              /*paramsl 队列的名称
                params2是否要持久化durable=false所谓持久化消息是否存盘，如果faise非持久化 true是持久化﹖非持久化会存盘吗?
                params3排他性,是否是独占独立
                params4是否自动删除，随着最后一个消费者消息完毕消息以后是否把队列自动删除
                params5携带附属参数*/

            //通过通创建交换机，声明队列，绑定关系，路由key,发送消息，和接收消息准备消息内容
            String queueNmae = "zqf";
            channel.queueDeclare(queueNmae,false, false, false, null);
            //准备消息内容
            String messge = "hello,zqf";
            //发送消息给队列queue
            channel.basicPublish("",queueNmae,null, messge.getBytes());

            System.out.println("消息成功发送");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭通道

            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            // 关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
