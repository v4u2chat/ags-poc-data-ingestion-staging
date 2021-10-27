package com.ags.poc.ingestion.staging;

import com.ags.poc.ingestion.chroniclemap.ChronicleMapWrapper;
import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.repository.CampaignDataRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses={CampaignDataRepository.class})
@EntityScan(basePackageClasses= CampaignDataInfo.class)
@ComponentScan(basePackages = "com.ags.poc.ingestion")
public class DataIngestionStagingApplication extends SpringBootServletInitializer{
	public static void main(final String[] args) throws Exception{

		SpringApplication.run(DataIngestionStagingApplication.class, args);
		System.out.println("Chronicle Map Initialization >> "+ ChronicleMapWrapper.getInstance().size());
	}
}
