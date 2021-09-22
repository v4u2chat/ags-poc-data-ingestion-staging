package com.ags.poc.ingestion.staging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "com.ags.poc.ingestion")
public class DataIngestionStagingApplication extends SpringBootServletInitializer{
	public static void main(String[] args) throws Exception{

		SpringApplication.run(DataIngestionStagingApplication.class, args);

	}
}
