package com.ags.poc.ingestion.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PreProcessorRoute extends RouteBuilder{

    @Autowired
    private AddUuidProcessor addUuidProcessor;

    @Autowired
    private BuildChronicleMapProcessor buildChronicleMapProcessor;

    @Override
    public void configure() throws Exception {

        final CsvDataFormat csvDataFormat = new CsvDataFormat()
                .setLazyLoad(true)
                .setUseMaps(true)
                .setDelimiter(',');


        this.from("{{inputPath}}"+"?preMove=inprogress&move=./processed/${date:now:yyyyMMdd}/${file:name}&moveFailed=./error/${file:name.noext}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}.failed")//("")
            .routeId("RAW-CSV-INGESTION-STAGING-READER")
            .onCompletion()
                .onCompleteOnly()
                    .to("log:sync")
                    .log("Successfully processed file named : ${file:name}")
                    .end()
            .unmarshal(csvDataFormat)
            .split(this.body())
            .streaming()
            .process(this.addUuidProcessor)
//            .log("Unmarshalled --> ${body}")
            .to("sql:{{sqlInsertStmt}}","seda:chronicleMap")
            ;

        this.from("seda:chronicleMap")
            .routeId("RAW-CSV-INGESTION-STAGING-CMAP")
            .split(this.body())
            .streaming()
            //.log("To ChronicleMap --> ${body}")
            .process(this.buildChronicleMapProcessor);
            // .to("chronicle-engine:~/sales-data-map.dat");
    }
    
}
