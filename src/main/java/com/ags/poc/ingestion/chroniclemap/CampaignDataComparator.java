package com.ags.poc.ingestion.chroniclemap;

import com.ags.poc.ingestion.entities.CampaignDataInfo;

import java.util.Comparator;

public class CampaignDataComparator  implements Comparator<CampaignDataInfo> {
    
    @Override
    public int compare(final CampaignDataInfo a, final CampaignDataInfo b){
        return new Integer(a.getPriority()).compareTo(new Integer(b.getPriority()));
    }
}
