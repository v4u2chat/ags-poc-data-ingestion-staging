package com.ags.poc.ingestion.chroniclemap;

import com.ags.poc.ingestion.entities.CampaignDataInfo;

import java.util.Map;
import java.util.concurrent.Callable;

public class ChronicleMapUpdateTask implements Callable<CampaignDataInfo>{

    private final Map<String,String> campaignDataInfoPayload;

    public ChronicleMapUpdateTask(final Map<String,String> campaignDataInfoPayload){
        this.campaignDataInfoPayload = campaignDataInfoPayload;
    }

    ChronicleMapWrapper chronicleMapWrapper = ChronicleMapWrapper.getInstance();
    
    @Override
    public CampaignDataInfo call() throws Exception
    {
        final CampaignDataInfo campaignDataInfo = this.buildCampaignInfoObject();
        this.chronicleMapWrapper.addElement(this.campaignDataInfoPayload.get("SOURCE_ACCOUNT_NBR"), campaignDataInfo);
        return campaignDataInfo;
    }

    private CampaignDataInfo buildCampaignInfoObject() {
        final CampaignDataInfo campaignDataInfo = new CampaignDataInfo();
        campaignDataInfo.setNbr(this.campaignDataInfoPayload.get("SOURCE_ACCOUNT_NBR"));
        campaignDataInfo.setCampaignType(this.campaignDataInfoPayload.get("PRODUCT_CODE"));
        campaignDataInfo.setMessage1(this.campaignDataInfoPayload.get("MESSAGE1"));
        campaignDataInfo.setMessage2(this.campaignDataInfoPayload.get("MESSAGE2"));
        campaignDataInfo.setMessage3(this.campaignDataInfoPayload.get("MESSAGE3"));
        campaignDataInfo.setMessage4(this.campaignDataInfoPayload.get("MESSAGE4"));
        campaignDataInfo.setPriority(Integer.parseInt(this.campaignDataInfoPayload.get("PRIORITY")));
        campaignDataInfo.setMaxVisitCount(Integer.parseInt(this.campaignDataInfoPayload.get("MAX_DISPLAY_COUNT")));
        campaignDataInfo.setVisitedCount(0);
        return campaignDataInfo;
    }
}
