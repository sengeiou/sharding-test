package com.wonders;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//使用多数据源配置  要去掉该类 阻止druid自动配置数据源
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableScheduling
@EnableAsync
@Slf4j
@EnableFeignClients
@ComponentScan("com.wonders")
public class ShardingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingTestApplication.class, args);
        log.debug("====================<<<<< debug level open !!  >>>>> ============================");
        log.info("====================<<<<< info level open  !!>>>>> ============================");
    }

}
