package com.ags.poc.ingestion.staging;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;

public class FileSplitter {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        CsvDataFormat csvParser = new CsvDataFormat(CSVFormat.DEFAULT);
        csvParser.setSkipHeaderRecord(true);
        csvParser.setQuoteMode(QuoteMode.ALL);
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                String fileName = "5m_Sales_Records.csv";
                int lineCount = 20;
                System.out.println("fileName = " + fileName);
                System.out.println("lineCount = " + lineCount);
                from("file:/Users/v4u2chat/Dev/data/inbox?noop=true&fileName=" + fileName).unmarshal(csvParser).split(body()).streaming()
                        .aggregate(constant(true), new ArrayListAggregationStrategy()).completionSize(lineCount)
                        .completionTimeout(1500).marshal(csvParser)
                        .to("file:/Users/v4u2chat/Dev/data/outbox?fileName=${file:name.noext}_${header.CamelSplitIndex}.csv");
            }
        });
        context.start();
        Thread.sleep(10000);
        csvParser.close();
        context.stop();
        context.close();
        System.out.println("End");
    }
}