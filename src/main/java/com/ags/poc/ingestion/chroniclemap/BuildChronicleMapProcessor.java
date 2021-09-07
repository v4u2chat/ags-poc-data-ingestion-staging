package com.ags.poc.ingestion.chroniclemap;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class BuildChronicleMapProcessor implements Processor{

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String,String> payload = exchange.getIn().getBody(Map.class);
        executor.submit(new ChronicleMapUpdateTask(payload));
    }
    

}
