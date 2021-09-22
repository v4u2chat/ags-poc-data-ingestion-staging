package com.ags.poc.ingestion.staging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignController {

	@GetMapping("/")
	public String hello() {
		return "Greetings from Spring Boot!";
	}

}