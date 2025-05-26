package com.own.jsonschemavalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JsonschemavalidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonschemavalidatorApplication.class, args);
	}

}
