package com.ags.poc.ingestion.staging;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


@Component
public class RestEndPointRoutes extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        restConfiguration().component("undertow").host("localhost").port(8080).bindingMode(RestBindingMode.json);
        rest("/api/camapign")
                .get("/?nbr={nbr}").produces("application/json").to("bean:campaignService?method=getCampaignDataInfo(${header.nbr})")
        ;
    }
    
}
