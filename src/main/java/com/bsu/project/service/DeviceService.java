package com.bsu.project.service;

import com.bsu.project.entity.Device;
import com.bsu.project.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gulshirin Berdiyeva
 */
@Service
public class DeviceService extends AbstractService<Device> {

    @Autowired
    public DeviceService(DeviceRepository repository) {
        super("Device", repository);
    }
}
