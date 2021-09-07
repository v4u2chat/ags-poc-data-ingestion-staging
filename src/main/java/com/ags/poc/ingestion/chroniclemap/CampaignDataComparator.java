package com.ags.poc.ingestion.chroniclemap;

import java.util.Comparator;

public class CampaignDataComparator  implements Comparator<CampaignDataInfo> {
    
    public int compare(CampaignDataInfo a, CampaignDataInfo b){
        return (a.getVisitedCount()+"-"+a.getPriority()).compareTo(b.getVisitedCount()+"-"+b.getPriority());
    }
}
