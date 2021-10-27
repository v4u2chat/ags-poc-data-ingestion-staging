package com.ags.poc.ingestion.staging;

import com.ags.poc.ingestion.chroniclemap.CampaignService;
import com.ags.poc.ingestion.entities.CampaignDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CampaignController {

	@Autowired
	private CampaignService campaignService;

	@GetMapping("/hello")
	public String hello() {
		return "Greetings from Spring Boot!\t"+new Date();
	}

	@GetMapping("/nbr")
	public CampaignDataInfo nbr(@RequestParam(value = "nbr", defaultValue = "-") final String nbr) {
		return this.campaignService.getCampaignDataInfo(nbr);
	}

}