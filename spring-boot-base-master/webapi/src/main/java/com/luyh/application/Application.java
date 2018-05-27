package com.luyh.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.luyh.config.security.SecurityConfig;
import com.luyh.projectv1.dao.config.HibernateConfig;

@Import({HibernateConfig.class,SecurityConfig.class})
@EntityScan("com.luyh")
@ComponentScan(basePackages={"com.luyh"})
@SpringBootApplication(scanBasePackages={"com.luyh"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
                    return application.sources(Application.class);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

    }
}


