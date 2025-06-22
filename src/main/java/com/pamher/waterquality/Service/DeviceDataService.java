package com.pamher.waterquality.Service;

import com.pamher.waterquality.pojo.DeviceData;

import java.util.List;

public interface DeviceDataService

{

    List<DeviceData> getAllDevices();
    DeviceData saveDeviceData(DeviceData deviceData); // 保存设备数据

}
