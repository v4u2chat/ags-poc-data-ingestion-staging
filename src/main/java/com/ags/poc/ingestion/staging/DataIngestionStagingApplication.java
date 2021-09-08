package com.ags.poc.ingestion.staging;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.openhft.chronicle.map.ChronicleMap;

@SpringBootApplication
@ComponentScan(basePackages = "com.ags.poc.ingestion")
public class DataIngestionStagingApplication {


	public static void main(String[] args) throws Exception{

		SpringApplication.run(DataIngestionStagingApplication.class, args);

	}
}
