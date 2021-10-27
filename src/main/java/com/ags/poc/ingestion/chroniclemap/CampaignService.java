package com.ags.poc.ingestion.chroniclemap;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.repository.CampaignDataRepository;
import com.ags.poc.ingestion.utils.PocUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignDataRepository campaignDataRepository;

    private final ChronicleMapWrapper chronicleMapWrapper = ChronicleMapWrapper.getInstance();
    
    public CampaignDataInfo getCampaignDataInfo(final String key){


        final List<CampaignDataInfo> campaignDataInfos = this.chronicleMapWrapper.getCampaignDataInfo(key);
        CampaignDataInfo campaignDataInfo = this.computeTheCampaignToBeDisplayed(key, campaignDataInfos);
        if (campaignDataInfo != null) {
            return campaignDataInfo;
        }
        else {
            campaignDataInfo = this.computeTheCampaignToBeDisplayed(key, this.campaignDataRepository.findByNbr(key));
            if (campaignDataInfo != null) {
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

            System.out.println("CampaignService >> key >> "+key+ "\t >> "+campaignDataInfo);

            if(campaignDataInfo.getMaxVisitCount()>=campaignDataInfo.getVisitedCount()){
                this.chronicleMapWrapper.addElement(key, campaignDataInfo);
                return campaignDataInfo;
            }
        }
        return null;
    }
}
