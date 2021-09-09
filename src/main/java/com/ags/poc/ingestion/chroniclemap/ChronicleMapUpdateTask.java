package com.ags.poc.ingestion.chroniclemap;

import java.util.Map;
import java.util.concurrent.Callable;

public class ChronicleMapUpdateTask implements Callable<CampaignDataInfo>{

    private Map<String,String> campaignDataInfoPayload;

    public ChronicleMapUpdateTask(Map<String,String> campaignDataInfoPayload){
        this.campaignDataInfoPayload = campaignDataInfoPayload;
    }

    ChronicleMapWrapper chronicleMapWrapper = ChronicleMapWrapper.getInstance();
    
    public CampaignDataInfo call() throws Exception
    {
        CampaignDataInfo campaignDataInfo = buildCampaignInfoObject();
        chronicleMapWrapper.addElement(campaignDataInfoPayload.get("SOURCE_ACCOUNT_NBR"), campaignDataInfo);
        return campaignDataInfo;
    }

    private CampaignDataInfo buildCampaignInfoObject() {
        CampaignDataInfo campaignDataInfo = new CampaignDataInfo();
        campaignDataInfo.setCampaignType(campaignDataInfoPayload.get("SOURCE_ACCOUNT_NBR"));
        campaignDataInfo.setMessage1(campaignDataInfoPayload.get("MESSAGE1"));
        campaignDataInfo.setMessage2(campaignDataInfoPayload.get("MESSAGE2"));
        campaignDataInfo.setMessage3(campaignDataInfoPayload.get("MESSAGE3"));
        campaignDataInfo.setMessage4(campaignDataInfoPayload.get("MESSAGE4"));
        campaignDataInfo.setPriority(Integer.parseInt(campaignDataInfoPayload.get("PRIORITY")));
        campaignDataInfo.setMaxVisitCount(Integer.parseInt(campaignDataInfoPayload.get("MAX_DISPLAY_COUNT")));
        campaignDataInfo.setVisitedCount(0);
        return campaignDataInfo;
    }
}
