package com.pamher.waterquality.mapper;

import com.pamher.waterquality.pojo.Device;
import com.pamher.waterquality.pojo.DeviceData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface DeviceMapper {

    // 查询所有设备
    List<Device> findAllDevices();

    // 查询设备信息
    Optional<Device> findDeviceById(Integer deviceId);

    // 获取设备的最新数据
    Optional<DeviceData> findLatestDeviceData(Integer deviceId);

    // 获取设备的历史数据
    List<DeviceData> findHistoricalDeviceData(Integer deviceId);

    // 保存设备信息
    void saveDevice(Device device);

    // 更新设备信息
   //void updateDevice(@Param("deviceId") Integer deviceId, @Param("updatedDevice") Device updatedDevice);

    // 更新设备信息
    void updateDevice(Device device);
    // 删除设备
    void deleteDevice(Integer deviceId);
}
