package com.ags.poc.ingestion.chroniclemap;

import java.util.Comparator;

public class CampaignDataComparator  implements Comparator<CampaignDataInfo> {
    
    public int compare(CampaignDataInfo a, CampaignDataInfo b){
        return ((a.getMaxVisitCount()-a.getVisitedCount())+"-"+a.getPriority()).compareTo(b.getMaxVisitCount()-b.getVisitedCount()+"-"+b.getPriority());
    }
}
