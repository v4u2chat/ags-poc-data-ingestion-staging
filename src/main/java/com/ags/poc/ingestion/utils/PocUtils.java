package com.ags.poc.ingestion.utils;

import com.ags.poc.ingestion.chroniclemap.CampaignDataInfo;

public class PocUtils {
    
    public static CampaignDataInfo createDummyCampaignInfo() {

        CampaignDataInfo campaignDataInfo = new CampaignDataInfo();
        campaignDataInfo.setCampaignType("--NONE--");
        campaignDataInfo.setMessage1("--NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE--");
        campaignDataInfo.setMessage2("--NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE--");
        campaignDataInfo.setMessage3("--NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE--");
        campaignDataInfo.setMessage4("--NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE----NONE--");
        campaignDataInfo.setPriority(9);
        campaignDataInfo.setVisitedCount(9);
        
        return campaignDataInfo;
    }
}
