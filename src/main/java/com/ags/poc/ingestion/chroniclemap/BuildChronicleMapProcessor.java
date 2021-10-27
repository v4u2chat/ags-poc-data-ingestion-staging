package com.ags.poc.ingestion.chroniclemap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class BuildChronicleMapProcessor implements Processor{

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void process(final Exchange exchange) throws Exception {

        final Map<String,String> payload = exchange.getIn().getBody(Map.class);
        executor.submit(new ChronicleMapUpdateTask(payload));
    }
    

}
