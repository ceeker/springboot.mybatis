package org.ceeker.web.sbootm.controller;

import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceDetectionController {

    @RequestMapping("/detect-device")
    @ResponseBody
    public String detectDevice(Device device) {
        
        String deviceType = "unknown";
        if (device.isNormal()) {
            deviceType = "normal";
        } else if (device.isMobile()) {
            deviceType = "mobile";
        } else if (device.isTablet()) {
            deviceType = "tablet";
        }
        return "this is a" + deviceType + " browser!";
    }

}
