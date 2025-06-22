package com.pamher.waterquality.mapper;

import com.pamher.waterquality.pojo.DeviceData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceDataMapper

{
    List<DeviceData> getAllDevices();
    void insertDeviceData(DeviceData deviceData);
}
