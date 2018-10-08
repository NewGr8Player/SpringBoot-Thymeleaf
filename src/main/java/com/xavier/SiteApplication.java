package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringBoot Starter
 */
@EnableCaching
@EnableSwagger2
@EnableScheduling
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableTransactionManagement(order = 2)
public class SiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}

}
