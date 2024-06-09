package com.agendaki.financially;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FinanciallyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanciallyApplication.class, args);
    }

}
