package com.software.controller;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.domain.GeneralManager;

@Slf4j
@RestController
@RequestMapping("api/generalmanager")
public class GeneralmanagerController {
	@RequestMapping("/queryById")
	public GeneralManager queryGManagerById(@RequestBody Map<String, String> param){
		log.info(LoginController.currentUserId+" "+"queryGManagerById : "+param);
		GeneralManager generalManager = new GeneralManager();
		generalManager.setId(param.get("id"));
		
		GeneralManager result = null;
		return result;
	}
	
	
}
