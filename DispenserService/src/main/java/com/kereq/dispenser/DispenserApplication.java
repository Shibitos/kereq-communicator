package com.kereq.dispenser;

import com.kereq.common.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Import(SecurityConfig.class)
@EnableEurekaClient
@EnableFeignClients
@EnableMongoAuditing
@EnableMongoRepositories
public class DispenserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispenserApplication.class, args);
	}
}