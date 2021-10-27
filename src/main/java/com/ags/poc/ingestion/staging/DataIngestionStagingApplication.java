package com.ags.poc.ingestion.staging;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.repository.CampaignDataRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

@EnableAsync
@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses={CampaignDataRepository.class})
@EntityScan(basePackageClasses= CampaignDataInfo.class)
@ComponentScan(basePackages = "com.ags.poc.ingestion")
@EnableBatchProcessing
public class DataIngestionStagingApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;


	public static void main(final String[] args) throws Exception{

		SpringApplication.run(DataIngestionStagingApplication.class, args);

	}

	@Override
	public void run(final String... args) throws Exception {
		final JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).addLong("time", System.currentTimeMillis()).toJobParameters();

		final JobExecution execution = this.jobLauncher.run(this.job, jobParameters);
		System.out.println("STATUS :: " + execution.getStatus());
	}
}
