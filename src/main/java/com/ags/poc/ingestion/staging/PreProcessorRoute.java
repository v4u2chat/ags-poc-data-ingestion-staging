package com.ags.poc.ingestion.staging;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.springframework.stereotype.Component;


@Component
public class PreProcessorRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        CsvDataFormat csvDataFormat = new CsvDataFormat()
                .setLazyLoad(true)
                .setUseMaps(true)
                .setDelimiter(',');


        from("{{inputPath}}"+"?noop=true&maxMessagesPerPoll=1&delay=5000")//("")
            .routeId("RAW-CSV-INGESTION-STAGING-READER")
            .unmarshal(csvDataFormat)
            .split(body())
            .streaming()
            .log("Unmarshalled --> ${body}")
            .to("sql:{{sqlInsertStmt}}","seda:chronicleMap");

        from("seda:chronicleMap")
            .routeId("RAW-CSV-INGESTION-STAGING-CMAP")
            .split(body())
            .streaming()
            .log("To ChronicleMap --> ${body}");
    }
    
}
