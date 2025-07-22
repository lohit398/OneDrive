package com.personal.development.cloud.GetService; 

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

    @GetMapping("/getfile")
    public String get() {
        // Implement get file functionality here
        // Connect to redis for the file metadata
        try {
            String host = InetAddress.getLocalHost().getHostName();
            return "GET request received from host: " + host;
        } catch (UnknownHostException e) {
            return "GET request received, but host name could not be determined.";
        }
    }
}