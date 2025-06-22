package com.pamher.waterquality.Controller;

import com.pamher.waterquality.Service.DeviceService;
import com.pamher.waterquality.pojo.Device;
import com.pamher.waterquality.pojo.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // 获取所有设备（已实现）
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }
    //根据ID查询某个设备（已实现）
    @GetMapping("/{deviceId}")
    public ResponseEntity<Device> findDeviceById(@PathVariable Integer deviceId) {
        Optional<Device> device = deviceService.findDeviceById(deviceId);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }




    // 添加新的设备（已实现）
    @PostMapping
    public ResponseEntity<String> addDevice(@RequestBody Device device) {
        // 添加数据验证
        if (device.getDeviceName() == null || device.getDeviceName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("设备名称不能为空");
        }
        if (device.getDeviceQuantity() < 0) {
            return ResponseEntity.badRequest().body("设备数量不能为负数");
        }

        deviceService.addDevice(device);
        return ResponseEntity.ok("设备添加成功");
    }

    // 更新设备信息(已实现)
    @PutMapping("/{deviceId}")
    public ResponseEntity<String> updateDevice(@PathVariable Integer deviceId, @RequestBody Device device) {
        // 直接使用路径参数的deviceId，避免从请求体获取可能为null的值
        device.setDeviceId(deviceId);  // 设置正确的deviceId

        try {
            deviceService.updateDevice(device);
            return ResponseEntity.ok("设备更新成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 删除设备(已实现)
    @DeleteMapping("/{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable Integer deviceId) {
        boolean success = deviceService.deleteDevice(deviceId);
        return success ? ResponseEntity.ok("设备删除成功") : ResponseEntity.badRequest().body("设备删除失败");
    }
}
