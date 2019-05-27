package com.tgrajkowski;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients("com.tgrajkowski")
@EnableDiscoveryClient
@SpringBootApplication
public class ReportGeneratorApplication {
//    @Value("${cloud-repository.own.value}")
//    private String myString;

    public static void main(String[] args) {
        SpringApplication.run(ReportGeneratorApplication.class, args);

    }

//    @Bean
//    public void initBean() {
//        System.out.println("checking is it really working...: "+myString);
//    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
