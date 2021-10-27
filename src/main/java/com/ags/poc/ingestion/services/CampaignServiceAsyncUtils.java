package com.ags.poc.ingestion.services;

import com.ags.poc.ingestion.chroniclemap.ChronicleMapWrapper;
import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.repository.CampaignDataRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CampaignServiceAsyncUtils {

	@Async
	public void updateTheVisitedCount(final ChronicleMapWrapper chronicleMapWrapper, final CampaignDataRepository campaignDataRepository, final String key, final CampaignDataInfo campaignDataInfo) {
		// 1. Update in Chronicle Map
		chronicleMapWrapper.addElement(key, campaignDataInfo);

		//2. Update in The Database
		final CampaignDataInfo dbCampaignDataInfo = campaignDataRepository.findById(campaignDataInfo.getId());
		if(dbCampaignDataInfo!=null){
			dbCampaignDataInfo.setVisitedCount(campaignDataInfo.getVisitedCount());
			campaignDataRepository.save(dbCampaignDataInfo);
		}
	}
}
