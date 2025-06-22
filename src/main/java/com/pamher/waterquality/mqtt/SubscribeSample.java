package com.pamher.waterquality.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SubscribeSample {
    public static void main(String[] args) {
        String broker = "tcp://broker.emqx.io:1883";
        String topic = "testtopic";
        String username = "root";
        String password = "root";
        String clientid = "subscribe_client";
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);

            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) {
                    try {
                        String payload = new String(message.getPayload());
                        System.out.println("Received message:");
                        System.out.println("Topic: " + topic);
                        System.out.println("Qos: " + message.getQos());
                        System.out.println("Content: " + payload);

                        // 转发数据到HTTP端点
                        HttpClient.sendDeviceData(payload);
                        System.out.println("Successfully forwarded data to HTTP endpoint");
                    } catch (Exception e) {
                        System.err.println("Error forwarding message to HTTP endpoint: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }
            });

            // 连接MQTT broker并订阅主题
            client.connect(options);
            client.subscribe(topic, qos);
            System.out.println("Connected to MQTT broker and subscribed to topic: " + topic);

            // 保持程序运行
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}