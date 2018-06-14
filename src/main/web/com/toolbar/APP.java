package com.toolbar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//放在最上面
@MapperScan(basePackages = "com.toolbar.mapper")
public class APP {
    public static void main(String[] args) {
        SpringApplication.run( APP.class,args );
    }
}
