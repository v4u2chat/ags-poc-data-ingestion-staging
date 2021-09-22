package com.ags.poc.ingestion.staging;

import java.util.Date;

import com.ags.poc.ingestion.chroniclemap.CampaignDataInfo;
import com.ags.poc.ingestion.chroniclemap.CampaignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignController {

	@Autowired
	private CampaignService campaignService;

	@GetMapping("/hello")
	public String hello() {
		return "Greetings from Spring Boot!\t"+new Date();
	}

	@GetMapping("/nbr")
	public CampaignDataInfo nbr(@RequestParam(value = "nbr", defaultValue = "-") String nbr) {
		return campaignService.getCampaignDataInfo(nbr);
	}

}