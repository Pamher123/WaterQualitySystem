package com.pamher.waterquality.Service;

import com.pamher.waterquality.mapper.DeviceMapper;
import com.pamher.waterquality.pojo.Device;
import com.pamher.waterquality.pojo.DeviceData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;

    @Autowired
    public DeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Override//获取所有设备
    public List<Device> getAllDevices() {
        return deviceMapper.findAllDevices();
    }


    @Override
    public void addDevice(Device device) {
        // 添加业务逻辑验证
        if (device.getDeviceName() == null || device.getDeviceName().trim().isEmpty()) {
            throw new IllegalArgumentException("设备名称不能为空");
        }
        deviceMapper.saveDevice(device);
    }

    // DeviceServiceImpl.java
    @Override
    @Transactional
    public void updateDevice(Device device) {
        if (device.getDeviceId() == null) {
            throw new IllegalArgumentException("设备ID不能为空");
        }

        Optional<Device> existingDevice = deviceMapper.findDeviceById(device.getDeviceId());
        if (existingDevice.isPresent()) {
            deviceMapper.updateDevice(device);
        } else {
            throw new IllegalArgumentException("设备未找到");
        }
    }


    //删除设备
    @Override
    @Transactional
    public boolean deleteDevice(Integer deviceId) {
        // 查询设备是否存在
        Optional<Device> existingDevice =deviceMapper.findDeviceById(deviceId);
        if (existingDevice.isPresent()) {
            // 删除设备
            deviceMapper.deleteDevice(deviceId);
            return true; // 删除成功
        }
        return false; // 设备不存在
    }

    @Override
    @Transactional
    public Optional<Device> findDeviceById(Integer deviceId) {
        System.out.println("正在查询设备ID: " + deviceId);  // 添加日志
        Optional<Device> device = deviceMapper.findDeviceById(deviceId);
        System.out.println("查询结果: " + device);  // 添加日志
        return device;
    }


}
