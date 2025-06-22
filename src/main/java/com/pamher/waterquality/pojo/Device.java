package com.pamher.waterquality.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
@Entity
@Table(name = "devices")
@Data
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成 device_id
    @Column(name = "device_id")
    private Integer deviceId;


    @Column(name = "device_name", nullable = false)
    private String deviceName;

    @Column(name = "device_quantity", nullable = false)
    private Integer deviceQuantity;

    @Column(name = "device_image")
    private byte[] deviceImage; // 以字节数组存储图像数据

}
