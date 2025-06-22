package com.pamher.waterquality.Service;

import com.pamher.waterquality.mapper.DeviceDataMapper;
import com.pamher.waterquality.mapper.DeviceMapper;
import com.pamher.waterquality.pojo.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceDataServiceImpl implements DeviceDataService
{
    private final DeviceDataMapper deviceDataMapper;

    @Autowired
    public DeviceDataServiceImpl(DeviceDataMapper deviceDataMapper) {
        this.deviceDataMapper = deviceDataMapper;
    }


    @Override
    public List<DeviceData> getAllDevices() {
        return deviceDataMapper.getAllDevices();  // 从数据库获取设备数据
    }

    @Override
    public DeviceData saveDeviceData(DeviceData deviceData) {
        deviceDataMapper.insertDeviceData(deviceData); // 调用Mapper来保存数据
        return deviceData; // 返回保存的数据对象（可选）
    }

}
