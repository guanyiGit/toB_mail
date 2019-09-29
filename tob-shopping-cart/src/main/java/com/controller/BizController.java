package com.controller;

import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/card")
public class BizController {

    private static final Logger logger = LoggerFactory.getLogger(BizController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping(value = "/discovery")
    public Object info() {
        List<String> services = discoveryClient.getServices();
        logger.info("services: {}",services);
        List<ServiceInstance> list = discoveryClient.getInstances(applicationName);
        for (ServiceInstance service : list) {
            logger.info("serviceId: {}", service.getServiceId());
            logger.info("uri: {}", service.getUri());
            logger.info("host: {}", service.getHost());
            logger.info("port: {}", service.getPort());
            logger.info("metadata: {}", service.getMetadata());
        }
        return this.discoveryClient;
    }
}
