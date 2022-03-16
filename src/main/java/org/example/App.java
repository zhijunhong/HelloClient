package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class App {
    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     * 实例化RestTemplate     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/select")
    public List<Map<String, Object>> select() {
        List<Map<String, Object>> data = new ArrayList<>();
        data = restTemplate.postForObject("http://service-provider/mysql/test/selectV2",null, List.class);
        return data;
    }
}
