package com.example.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 最多可以查询6张图片
 * 获取的数据进行切割
 * 数据找到之后，通过uri从oss中获取
 */
@MapperScan(basePackages = "com.example.carousel.mapper")
@SpringBootApplication
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class, args);
    }
}
