package com.brushmyskills.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/unsecure")
@RestController
public class UnSecureRestEndPointController {
	
	@GetMapping("/all")
	public String getAllUnSecuredResource() {
		
		return "Returning all resources from unsecured Rest End Point";
		
	}

}
