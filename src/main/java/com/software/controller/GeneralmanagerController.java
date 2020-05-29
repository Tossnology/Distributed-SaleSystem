package com.software.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.GeneralManager;

@RestController
@RequestMapping("/generalmanager")
public class GeneralmanagerController {
	@RequestMapping("/queryById")
	public GeneralManager queryGManagerById(@RequestBody Map<String, String> param){
		GeneralManager generalManager = new GeneralManager();
		generalManager.setId(param.get("id"));
		
		GeneralManager result = null;
		return result;
	}
	
	
}
