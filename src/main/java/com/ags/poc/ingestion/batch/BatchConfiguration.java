package com.ags.poc.ingestion.batch;

import com.ags.poc.ingestion.chroniclemap.ChronicleMapUpdateTask;
import com.ags.poc.ingestion.chroniclemap.ChronicleMapWrapper;
import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.services.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private final Logger logger = LoggerFactory.getLogger(CampaignService.class);
	private static final ExecutorService executor = Executors.newFixedThreadPool(100);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Autowired
	private CampaignDataInfoItemProcessor campaignDataInfoItemProcessor;

	@Bean
	public JdbcCursorItemReader<CampaignDataInfo> cursorItemReader(){
		final JdbcCursorItemReader<CampaignDataInfo> reader = new JdbcCursorItemReader<>();
		reader.setSql("SELECT ID, SOURCE_ACCOUNT_NBR, PRODUCT_CODE, MAX_DISPLAY_COUNT, VISITED_COUNT, UPLOAD_DATE, DELETE_DATE, CAMPAIGN_TYPE, MESSAGE1, MESSAGE2, MESSAGE3, MESSAGE4, PRIORITY, ACTION, SOURCE_ACCOUNT_NBR_MD5 FROM campaigndata ORDER BY ID ASC");
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(100);
		reader.setRowMapper(new CampaignDataInfoRowMapper());

		return reader;
	}

	// This is Thread-safe
	@Bean
	public JdbcPagingItemReader<CampaignDataInfo> pagingItemReader(){
		final JdbcPagingItemReader<CampaignDataInfo> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(1000);
		reader.setRowMapper(new CampaignDataInfoRowMapper());

		final Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("ID", Order.ASCENDING);

		final MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("SELECT ID, SOURCE_ACCOUNT_NBR, PRODUCT_CODE, MAX_DISPLAY_COUNT, VISITED_COUNT, UPLOAD_DATE, DELETE_DATE, CAMPAIGN_TYPE, MESSAGE1, MESSAGE2, MESSAGE3, MESSAGE4, PRIORITY, ACTION, SOURCE_ACCOUNT_NBR_MD5");
		queryProvider.setFromClause("FROM campaigndata");
		queryProvider.setSortKeys(sortKeys);

		reader.setQueryProvider(queryProvider);

		return reader;
	}
	@Bean
	public ItemWriter<CampaignDataInfo> customerItemWriter(){
		return items -> {
			for(final CampaignDataInfo campaignDataInfo : items) {
				executor.submit(new ChronicleMapUpdateTask(null,campaignDataInfo));
			}
			this.logger.info(items.size()+" are pushed Chronicle Map");
		};
	}


	@Bean
	public Step updateChronicleMapFromDatabase() {
		return this.stepBuilderFactory.get("updateChronicleMapFromDatabase").<CampaignDataInfo, CampaignDataInfo> chunk(10000)
				.reader(this.pagingItemReader())
				.processor(this.campaignDataInfoItemProcessor)
				.writer(this.customerItemWriter())
				.build();
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1")
				.<CampaignDataInfo, CampaignDataInfo> chunk(10000)
				.reader(this.cursorItemReader())
				.reader(this.pagingItemReader())
				.writer(this.customerItemWriter())
				.build();
	}

	@Bean
	public Job job() {
		System.out.println("Chronicle Map Initialization >> "+ ChronicleMapWrapper.getInstance().size());

		return this.jobBuilderFactory.get("job")
				.start(this.step1())
				.build();
	}
}
