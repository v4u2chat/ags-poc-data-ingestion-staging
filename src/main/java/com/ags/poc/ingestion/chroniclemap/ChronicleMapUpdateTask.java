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

        System.out.println("\n\n\n chronicleMapWrapper size "+ chronicleMapWrapper.size() + " \t\t "+chronicleMapWrapper.getVal(campaignDataInfoPayload.get("SOURCE_ACCOUNT_NBR")));
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
        campaignDataInfo.setVisitedCount(0);
        return campaignDataInfo;
    }
}
