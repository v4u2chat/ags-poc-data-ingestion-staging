package com.ags.poc.ingestion.chroniclemap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class ChronicleMapWrapper {
    
    private static ChronicleMapWrapper ourInstance = new ChronicleMapWrapper();

    public static synchronized ChronicleMapWrapper getInstance() {
        ourInstance.init();
        return ourInstance;
    }
    private ChronicleMapWrapper() {

    }

    private boolean isInit = false;
    private Map<String,List<CampaignDataInfo>> pMap;

    public synchronized void init(){
        if (isInit) return;
        try {

            pMap = new ConcurrentHashMap<>();
            // pMap = ChronicleMap
            //         .of(String.class, Queue.class).name("simple-pstore")
            //         .entries(10_000_000)
            //         .averageKey(UUID.randomUUID().toString())
            //         .createOrRecoverPersistedTo(new File(System.getProperty("user.home") + "/ags-campaign-data-map.dat"));
            // Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            //     @Override
            //     public void run() {
            //         pMap.close();
            //     }
            // }));
            isInit = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // public void close(){
    //     pMap.close();
    // }


    public List<CampaignDataInfo> getCampaignDataInfo(final String key){
        return pMap.get(key);
    }

    public void addElement(final String key , final CampaignDataInfo campaignDataInfo){
        System.out.println(key +"-----------------"+campaignDataInfo);
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
