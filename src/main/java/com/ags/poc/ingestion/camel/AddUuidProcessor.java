package com.ags.poc.ingestion.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class AddUuidProcessor implements Processor{

    @Override
    public void process(final Exchange exchange) throws Exception {

        final Map<String,String> payload = exchange.getIn().getBody(Map.class);
        payload.put("UUID", UUID.randomUUID().toString());
        exchange.getMessage().setBody(payload);
    }
    

}
