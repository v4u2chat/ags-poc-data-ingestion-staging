package com.ags.poc.ingestion.chroniclemap;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

public class ChronicleMapUpdateTask implements Callable<CampaignDataInfo>{
	Logger logger = LoggerFactory.getLogger(ChronicleMapUpdateTask.class);

    private final CampaignDataInfo campaignDataInfoPayloadObject;
    private final Map<String,String> campaignDataInfoPayloadMap;

    public ChronicleMapUpdateTask(final Map<String,String> campaignDataInfoPayload, final CampaignDataInfo campaignDataInfoPayloadObject){

        this.campaignDataInfoPayloadMap = campaignDataInfoPayload;
        this.campaignDataInfoPayloadObject = campaignDataInfoPayloadObject;
    }

    ChronicleMapWrapper chronicleMapWrapper = ChronicleMapWrapper.getInstance();
    
    @Override
    public CampaignDataInfo call() throws Exception
    {
        try {
            final CampaignDataInfo campaignDataInfo = this.campaignDataInfoPayloadObject ==null ? this.buildCampaignInfoObject() : this.campaignDataInfoPayloadObject;
            this.chronicleMapWrapper.addElement(campaignDataInfo.getNbr(), campaignDataInfo);
            this.logger.info("Total Size of Chronicle Map is "+ this.chronicleMapWrapper.size());
            return campaignDataInfo;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CampaignDataInfo buildCampaignInfoObject() {
        final CampaignDataInfo campaignDataInfo = new CampaignDataInfo();
        campaignDataInfo.setId(this.campaignDataInfoPayloadMap.get("UUID"));
        campaignDataInfo.setNbr(this.campaignDataInfoPayloadMap.get("SOURCE_ACCOUNT_NBR"));
        campaignDataInfo.setCampaignType(this.campaignDataInfoPayloadMap.get("PRODUCT_CODE"));
        campaignDataInfo.setMessage1(this.campaignDataInfoPayloadMap.get("MESSAGE1"));
        campaignDataInfo.setMessage2(this.campaignDataInfoPayloadMap.get("MESSAGE2"));
        campaignDataInfo.setMessage3(this.campaignDataInfoPayloadMap.get("MESSAGE3"));
        campaignDataInfo.setMessage4(this.campaignDataInfoPayloadMap.get("MESSAGE4"));
        campaignDataInfo.setPriority(Integer.parseInt(this.campaignDataInfoPayloadMap.get("PRIORITY")));
        campaignDataInfo.setMaxVisitCount(Integer.parseInt(this.campaignDataInfoPayloadMap.get("MAX_DISPLAY_COUNT")));
        campaignDataInfo.setVisitedCount(0);
        return campaignDataInfo;
    }
}
