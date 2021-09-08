package com.ags.poc.ingestion.chroniclemap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                    .entries(10_00_00_000)  //  10 Crores || Hundred Million
                    .averageKey("1234123412341234")
                    .averageValue(createDummyList())
                    .createOrRecoverPersistedTo(new File(System.getProperty("user.home") + "/ags-campaign-data-map.dat"));
                    
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    pMap.close();
                }
            }));
            isInit = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private List<CampaignDataInfo> createDummyList() {
        CampaignDataInfo campaignDataInfo = new CampaignDataInfo();
        campaignDataInfo.setCampaignType("campaignType");
        campaignDataInfo.setMessage1("message1message1message1message1message1message1message1message1message1message1message1message1message1");
        campaignDataInfo.setMessage2("message1message1message1message1message1message1message1message1message1message1message1message1message1");
        campaignDataInfo.setMessage3("message1message1message1message1message1message1message1message1message1message1message1message1message1");
        campaignDataInfo.setMessage4("message1message1message1message1message1message1message1message1message1message1message1message1message1");
        campaignDataInfo.setPriority(9);
        campaignDataInfo.setVisitedCount(9);

        List<CampaignDataInfo> dumCampaignDataInfos = new ArrayList<>();
        dumCampaignDataInfos.add(campaignDataInfo);
        dumCampaignDataInfos.add(campaignDataInfo);
        dumCampaignDataInfos.add(campaignDataInfo);
        dumCampaignDataInfos.add(campaignDataInfo);
        dumCampaignDataInfos.add(campaignDataInfo);
        return dumCampaignDataInfos;
    }


    public void close(){
        pMap.close();
        // pMap = null;
    }


    public List<CampaignDataInfo> getCampaignDataInfo(final String key){
        return pMap.get(key);
    }

    public void addElement(final String key , final CampaignDataInfo campaignDataInfo){
        List<CampaignDataInfo> campaignDataInfos = pMap.get(key) == null ? new ArrayList<CampaignDataInfo>() : pMap.get(key);
        campaignDataInfos.add(campaignDataInfo);
        pMap.put(key,campaignDataInfos);

        System.out.println("Added "+campaignDataInfo +" to "+ key);
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
