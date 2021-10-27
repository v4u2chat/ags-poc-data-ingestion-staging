package com.ags.poc.ingestion.services;

import com.ags.poc.ingestion.chroniclemap.CampaignDataComparator;
import com.ags.poc.ingestion.chroniclemap.ChronicleMapWrapper;
import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.repository.CampaignDataRepository;
import com.ags.poc.ingestion.utils.PocUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CampaignService {

    Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    private CampaignDataRepository campaignDataRepository;

    @Autowired
    private CampaignServiceAsyncUtils campaignServiceAsyncUtils;

    private final ChronicleMapWrapper chronicleMapWrapper = ChronicleMapWrapper.getInstance();
    
    public CampaignDataInfo getCampaignDataInfo(final String key){

        final List<CampaignDataInfo> campaignDataInfos = this.chronicleMapWrapper.getCampaignDataInfo(key);
        CampaignDataInfo campaignDataInfo = this.computeTheCampaignToBeDisplayed(key, campaignDataInfos);
        if (campaignDataInfo != null) {
            this.logger.info("Found "+key+ " from ChronicleMap");
            return campaignDataInfo;
        }
        else {
            campaignDataInfo = this.computeTheCampaignToBeDisplayed(key, this.campaignDataRepository.findByNbr(key));
            if (campaignDataInfo != null) {
                this.logger.info("Found "+key+ " from Database (( Fallback Mechanism ))");
                return campaignDataInfo;
            }
        }
        return PocUtils.createDummyCampaignInfo();
    }

    @Nullable
    private CampaignDataInfo computeTheCampaignToBeDisplayed(final String key, final List<CampaignDataInfo> campaignDataInfos) {
        if(campaignDataInfos!=null && campaignDataInfos.size()>0){
            Collections.sort(campaignDataInfos,new CampaignDataComparator());
            final CampaignDataInfo campaignDataInfo = campaignDataInfos.get(0);
            if(campaignDataInfo.getMaxVisitCount()>=campaignDataInfo.getVisitedCount()){
                this.campaignServiceAsyncUtils.updateTheVisitedCount(this.chronicleMapWrapper, this.campaignDataRepository, key, campaignDataInfo);
                return campaignDataInfo;
            }
        }
        return null;
    }
}
