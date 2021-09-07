package com.ags.poc.ingestion.staging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ags.poc.ingestion")
public class DataIngestionStagingApplication {

	public static void main(String[] args) throws Exception{

		// ChronicleMap<String, String> persistedCountryMap = ChronicleMap.of(String.class, String.class)
  		// 	.name("sales-data-map")
  		// 	.entries(5000000)
		// 	.averageKey("AmericaAmericaAmerica")
		// 	.averageValue("AmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmericaAmerica")  
		// 	// .create();
  		// 	.createPersistedTo(new File(System.getProperty("user.home") + "/sales-data-map.dat"));

		// System.out.println(persistedCountryMap.size());	  

		SpringApplication.run(DataIngestionStagingApplication.class, args);



	}

}
