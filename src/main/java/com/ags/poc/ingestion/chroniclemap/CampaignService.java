package com.ags.poc.ingestion.chroniclemap;

import java.util.Collections;
import java.util.List;

import com.ags.poc.ingestion.utils.PocUtils;

import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    ChronicleMapWrapper chronicleMapWrapper = ChronicleMapWrapper.getInstance();
    
    public CampaignDataInfo getCampaignDataInfo(String key){
        System.out.println("CampaignService >> key >> "+key);

        List<CampaignDataInfo> campaignDataInfos = chronicleMapWrapper.getCampaignDataInfo(key);
        if(campaignDataInfos!=null){
            Collections.sort(campaignDataInfos,new CampaignDataComparator());
            CampaignDataInfo campaignDataInfo = campaignDataInfos.get(0);
            campaignDataInfos.remove(0);

            if(campaignDataInfo.getMaxVisitCount()>campaignDataInfo.getVisitedCount()){
                campaignDataInfo.incrementVisitedCount();
                chronicleMapWrapper.addElement(key, campaignDataInfo);
                return campaignDataInfo;
            }
        }
        return PocUtils.createDummyCampaignInfo();
    }
}
