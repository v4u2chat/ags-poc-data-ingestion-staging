package com.ags.poc.ingestion.staging;

import com.ags.poc.ingestion.chroniclemap.BuildChronicleMapProcessor;
import com.ags.poc.ingestion.chroniclemap.CampaignService;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PreProcessorRoute extends RouteBuilder{

    @Autowired
    private BuildChronicleMapProcessor buildChronicleMapProcessor;

    @Autowired
    private CampaignService campaignService;

    @Override
    public void configure() throws Exception {


        restConfiguration().component("undertow").host("localhost").port(8080).bindingMode(RestBindingMode.json);
        rest("/api/camapign")
                .get("/?nbr={nbr}").produces("application/json").to("bean:campaignService?method=getCampaignDataInfo(${header.nbr})")
        ;

     
        CsvDataFormat csvDataFormat = new CsvDataFormat()
                .setLazyLoad(true)
                .setUseMaps(true)
                .setDelimiter(',');


        from("{{inputPath}}"+"?preMove=inprogress&move=./processed/${date:now:yyyyMMdd}/${file:name}&moveFailed=./error/${file:name.noext}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}.failed")//("")
            .routeId("RAW-CSV-INGESTION-STAGING-READER")
            // .onCompletion()
            //     .onFailureOnly()
            //         .to("log:sync")
            //         .log("Failed to process file named : ${file:name}")
            //         .end()
            .onCompletion()        
                .onCompleteOnly()
                    .to("log:sync")
                    .log("Successfully processed file named : ${file:name}")
                    .end()
            .unmarshal(csvDataFormat)
            .split(body())
            .streaming()
            //.log("Unmarshalled --> ${body}")
            .to("sql:{{sqlInsertStmt}}","seda:chronicleMap")
            ;

        from("seda:chronicleMap")
            .routeId("RAW-CSV-INGESTION-STAGING-CMAP")
            .split(body())
            .streaming()
            //.log("To ChronicleMap --> ${body}")
            .process(buildChronicleMapProcessor);
            // .to("chronicle-engine:~/sales-data-map.dat");
    }
    
}
