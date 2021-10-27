package com.ags.poc.ingestion.batch;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CampaignDataInfoItemProcessor implements ItemProcessor<CampaignDataInfo,CampaignDataInfo> {

	@Override
	public CampaignDataInfo process(final CampaignDataInfo campaignDataInfo){
		return campaignDataInfo;
	}
}
