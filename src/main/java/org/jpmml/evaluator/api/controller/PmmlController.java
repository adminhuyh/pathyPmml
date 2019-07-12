package org.jpmml.evaluator.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.jpmml.evaluator.api.service.PmmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/pmml/predict")
public class PmmlController {
	
	@Autowired
	PmmlService pmmlService;
	
	@RequestMapping("/test")
	public String test(){
		return "ok";
	}
	
	@RequestMapping("/testPredict")
	public String testPredict(){
		Map lrHeartInputMap = new HashMap();
		lrHeartInputMap.put("user_id","N2YxZTI0N2Q3NWQ1M2M0MzQ4YzQ2YmI3ZGU5ZjU0NDhjM2NiZDg1NmQwMzQ2MzZmMDkzMTIyODk4ODUyYjcyYw==");
		lrHeartInputMap.put("pos_id", "351");
		lrHeartInputMap.put("ad_id", "31690");
		lrHeartInputMap.put("time_stamp","1561473879");
		lrHeartInputMap.put("page", "Keyword");
		lrHeartInputMap.put("network","4g");
		lrHeartInputMap.put("app_category_c1","在线视频");
		lrHeartInputMap.put("rating","9");	
		pmmlService.testPredict(lrHeartInputMap);
		return "ok";
	}

}
