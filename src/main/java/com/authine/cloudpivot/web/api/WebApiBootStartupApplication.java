package com.authine.cloudpivot.web.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author longhai
 */
@SpringBootApplication(
        scanBasePackages = {"com.authine.cloudpivot.web.api", "com.authine.cloudpivot.web.sso", "com.authine.cloudpivot.ext.controller"}
)
@EnableSwagger2
@EnableRedisHttpSession
@MapperScan(value = "com.authine.cloudpivot.web.api.mapper")
@EnableCaching
public class WebApiBootStartupApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApiBootStartupApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApiBootStartupApplication.class);
    }
}
