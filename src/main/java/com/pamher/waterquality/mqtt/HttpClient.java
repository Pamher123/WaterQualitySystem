package com.pamher.waterquality.mqtt;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClient {
    private static final String DEVICE_DATA_ENDPOINT = "http://localhost:8081/device-data";

    public static void sendDeviceData(String payload) throws Exception {
        URL url = new URL(DEVICE_DATA_ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            // 设置请求方法和头信息
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送请求体
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 获取响应
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed to send data to server. Response code: " + responseCode);
            }
        } finally {
            conn.disconnect();
        }
    }
}