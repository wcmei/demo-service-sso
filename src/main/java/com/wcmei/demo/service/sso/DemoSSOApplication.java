package com.wcmei.demo.service.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
@EnableSwagger2
@EnableDiscoveryClient
@MapperScan(value = {"com.wcmei.demo.service.sso.mapper"})
@SpringBootApplication(scanBasePackages = "com.wcmei.demo")
public class DemoSSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSSOApplication.class, args);
    }
    //指定启动配置文件
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(RedisApplication.class)
//                .properties(DevConstants.SPRING_PROFILE)
//                .run(args);
//        Main.main(args);
//    }
//
//    public class DevConstants {
    //对应：bootstrap-dev.properties配置文件
//        public static final String SPRING_PROFILE = "spring.profiles.active=dev";
//    }
}
