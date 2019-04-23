package com.scd.logbackstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.scd"})
public class LogbackstuApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogbackstuApplication.class, args);
	}

}
