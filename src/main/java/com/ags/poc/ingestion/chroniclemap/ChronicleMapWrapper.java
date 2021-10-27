package com.ags.poc.ingestion.chroniclemap;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import com.ags.poc.ingestion.utils.PocUtils;
import net.openhft.chronicle.map.ChronicleMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ChronicleMapWrapper {
    private final Logger logger = LoggerFactory.getLogger(ChronicleMapWrapper.class);
    private static final ChronicleMapWrapper ourInstance = new ChronicleMapWrapper();

    public static synchronized ChronicleMapWrapper getInstance() {
        ourInstance.init();
        return ourInstance;
    }
    private ChronicleMapWrapper() {

    }

    private boolean isInit = false;
    private ChronicleMap<String,List> pMap;
    public synchronized void init(){
        if (this.isInit) {
            return;
        }
        try {

            this.deleteExistingFileIfAny();

            this.pMap = ChronicleMap
                    .of(String.class, List.class).name("ags-campaign-data-map")
                    .entries(10_000)  //  10 Crores || Hundred Million
                    .averageKey("1234123412341234")
                    .averageValue(this.createDummyList())
                    .createOrRecoverPersistedTo(new File(System.getProperty("user.home") + "/ags-campaign-data-map.dat"));
                    
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    ChronicleMapWrapper.this.pMap.close();
                    ChronicleMapWrapper.this.deleteExistingFileIfAny();
                    System.out.println("ChronicleMap closed gracefully...");
                }
            }));
            this.isInit = true;
        }catch (final Exception e){
            e.printStackTrace();
        }
    }

    private void deleteExistingFileIfAny() {
        try {
            Files.deleteIfExists(new File(System.getProperty("user.home") + "/ags-campaign-data-map.dat").toPath());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private List<CampaignDataInfo> createDummyList() {

        final List<CampaignDataInfo> dumCampaignDataInfos = new ArrayList<>();
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        dumCampaignDataInfos.add(PocUtils.createDummyCampaignInfo());
        return dumCampaignDataInfos;
    }


    public void close(){
        this.pMap.close();
    }


    public List<CampaignDataInfo> getCampaignDataInfo(final String key){
        return this.pMap.get(key);
    }

    public void addElement(final String key , final CampaignDataInfo campaignDataInfo){
        final List<CampaignDataInfo> campaignDataInfos = this.pMap.get(key) == null ? new ArrayList<>() : this.pMap.get(key);

        for(final CampaignDataInfo campaignDataInfo2 : campaignDataInfos){
            if(campaignDataInfo2.getCampaignType().equals(campaignDataInfo.getCampaignType())){
                campaignDataInfos.remove(campaignDataInfo2);
                campaignDataInfo.incrementVisitedCount();
                break;
            }
        }
        if(campaignDataInfo.getMaxVisitCount()>campaignDataInfo.getVisitedCount()){
            campaignDataInfos.add(campaignDataInfo);
        }

        this.pMap.put(key,campaignDataInfos);

        this.logger.info("Added "+ campaignDataInfo.getCampaignType()+ " >> "+campaignDataInfos.size() +" to "+ campaignDataInfos);
    }

    public boolean exists(final String key){
        return this.pMap.containsKey(key);
    }

    public  List<CampaignDataInfo> getVal(final String key){
        return this.pMap.get(key);
    }

    public int size(){
        return this.pMap.size();
    }
}
