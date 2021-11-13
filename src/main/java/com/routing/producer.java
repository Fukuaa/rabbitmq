package com.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class producer {
    public static void main(String[] args) {
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
            String queueNmae = "zqq";
            channel.queueDeclare(queueNmae,false, false, false, null);
            //准备消息内容
            String messge = "2021年11月13日19点28分";
            String exchangename = "amq.topic";
            String routeKey = ".zqf.";
            String exchangtype = "topic";
            /*//声明交换机
            channel.exchangeDeclare(exchangename,exchangtype,true);
            //声明队列
            channel.queueDeclare("zqqf",true,false,false,null);
            //绑定关系
            channel.queueBind("zqqf",exchangename,"zq");
            //发送消息给队列queue*/
            for(int i=20;i>0;i--){
                String messge1 = "2021年11月13日19点28分"+i;
                channel.basicPublish(exchangename,routeKey,null, messge1.getBytes());
            }


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
