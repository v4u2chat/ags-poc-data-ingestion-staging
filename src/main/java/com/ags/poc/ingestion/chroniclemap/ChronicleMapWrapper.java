package com.ags.poc.ingestion.chroniclemap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ags.poc.ingestion.utils.PocUtils;

import net.openhft.chronicle.map.ChronicleMap;

public class ChronicleMapWrapper {
    
    private static ChronicleMapWrapper ourInstance = new ChronicleMapWrapper();

    public static synchronized ChronicleMapWrapper getInstance() {
        ourInstance.init();
        return ourInstance;
    }
    private ChronicleMapWrapper() {

    }

    private boolean isInit = false;
    private ChronicleMap<String,List> pMap;
    public synchronized void init(){
        if (isInit) return;
        try {

            pMap = ChronicleMap
                    .of(String.class, List.class).name("ags-campaign-data-map")
                    .entries(10_000)  //  10 Crores || Hundred Million
                    .averageKey("1234123412341234")
                    .averageValue(createDummyList())
                    .createOrRecoverPersistedTo(new File(System.getProperty("user.home") + "/ags-campaign-data-map.dat"));
                    
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    pMap.close();
                    System.out.println("ChronicleMap closed gracefully...");
                }
            }));
            isInit = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private List<CampaignDataInfo> createDummyList() {

        List<CampaignDataInfo> dumCampaignDataInfos = new ArrayList<>();
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        return dumCampaignDataInfos;
    }


    public void close(){
        pMap.close();
    }


    public List<CampaignDataInfo> getCampaignDataInfo(final String key){
        return pMap.get(key);
    }

    public void addElement(final String key , final CampaignDataInfo campaignDataInfo){
        List<CampaignDataInfo> campaignDataInfos = pMap.get(key) == null ? new ArrayList<CampaignDataInfo>() : pMap.get(key);

        for(CampaignDataInfo campaignDataInfo2 : campaignDataInfos){
            if(campaignDataInfo2.getCampaignType().equals(campaignDataInfo.getCampaignType())){
                campaignDataInfos.remove(campaignDataInfo2);
                campaignDataInfo.incrementVisitedCount();
                break;
            }
        }
        if(campaignDataInfo.getMaxVisitCount()>campaignDataInfo.getVisitedCount()){
            campaignDataInfos.add(campaignDataInfo);
        }
        
        pMap.put(key,campaignDataInfos);

        System.out.println("Added "+ campaignDataInfo.getCampaignType()+ " >> "+campaignDataInfos.size() +" to "+ campaignDataInfos);
    }

    public boolean exists(final String key){
        return pMap.containsKey(key);
    }

    public  List<CampaignDataInfo> getVal(final String key){
        return pMap.get(key);
    }

    public int size(){
        return pMap.size();
    }
}
