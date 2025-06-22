package com.pamher.waterquality.Controller;

import com.pamher.waterquality.Service.DeviceDataService;

import com.pamher.waterquality.pojo.DeviceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RequestMapping("/device-data")
@RestController
public class DeviceDataController

{
    private final DeviceDataService deviceDataService;

    // 构造函数注入
    @Autowired
    public DeviceDataController(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }


    // 获取设备实时数据（完成）
    @GetMapping
    public ResponseEntity<List<DeviceData>> getAllData()
    {
        try {
            List<DeviceData> deviceDataList = deviceDataService.getAllDevices();
            if (deviceDataList.isEmpty()) {
                log.info("No device data found.");
                return ResponseEntity.noContent().build();  // 返回204 No Content
            }
            log.info("Fetched {} device data records", deviceDataList.size());
            return ResponseEntity.ok(deviceDataList);
        } catch (Exception e) {
            log.error("Error fetching device data: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);  // 服务器内部错误
        }
    }

//接收/添加数据

@PostMapping
public ResponseEntity<DeviceData> addDeviceData(@RequestBody DeviceData deviceData) {
    try {
        DeviceData savedData = deviceDataService.saveDeviceData(deviceData);
        return ResponseEntity.ok(savedData); // 返回保存后的数据
    } catch (Exception e) {
        return ResponseEntity.status(500).build(); // 服务器错误
    }
}


}
