package com.pamher.waterquality.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "device_data")
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增长策略
    @Column(name = "data_id")
    private Integer dataId; // 数据ID

    @ManyToOne(fetch = FetchType.LAZY) // 设备和设备数据之间的多对一关系

    private Device device; // 关联设备实体

    @Column(name = "water_temperature")
    private Double waterTemperature; // 水温

    @Column(name = "oxygen_content")
    private Double oxygenContent; // 含氧量

    @Column(name = "nitrogen_content")
    private Double nitrogenContent; // 含氮量

    @Column(name = "phosphorus_content")
    private Double phosphorusContent; // 含磷量

    @Column(name = "ph_value")
    private Double phValue; // PH值

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp; // 时间戳，默认当前时间

    @Column(name = "turbidity")
    private Double turbidity; // 浑浊度
}
