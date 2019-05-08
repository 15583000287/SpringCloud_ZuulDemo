package com.example.zuul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableZuulProxy //开启zuul网关代理
@SpringBootApplication
@RefreshScope  // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候自动刷新
@RestController
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    //zuul配置能够使用config实现实时跟新
//    @ConfigurationProperties("zuul")
//    public ZuulProperties zuulProperties(){
//        return new ZuulProperties();
//    }
    @Value("${zuul.routes.api-a.path}")
    String path;
    @GetMapping("/getconfig")
    public String test(){
        System.out.println("spring.datasource.type ="+path);
        return path;
    }
}
