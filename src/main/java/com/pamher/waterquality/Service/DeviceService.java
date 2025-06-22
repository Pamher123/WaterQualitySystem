package com.pamher.waterquality.Service;

import com.pamher.waterquality.pojo.Device;
import com.pamher.waterquality.pojo.DeviceData;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    //获取全部设备
    List<Device> getAllDevices();
   //增加设备
    void addDevice(Device device);
    // 更新设备
    void updateDevice(Device device);
    //删除设备
    boolean deleteDevice(Integer deviceId);
    //根据设备ID查询设备
    Optional<Device> findDeviceById(Integer deviceId);
}
