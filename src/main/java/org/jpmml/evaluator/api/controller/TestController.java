package org.jpmml.evaluator.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jpmml.evaluator.api.service.PmmlService;
import org.jpmml.evaluator.api.service.TestService;
import org.jpmml.evaluator.api.util.HttpReqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.sf.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping(value="/test/predict")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/test1")
	public String test(){
		return "ok";
	}
	
	@RequestMapping("/test2")
	public String testJson(HttpServletRequest request, HttpServletResponse responese){
		
		
		return "ok";
	}
	
	@RequestMapping("/test3")
	public Map returnJson(HttpServletRequest request, HttpServletResponse responese){
		Map map = new JSONObject();
		map.put("success", "ok");
		map.put("message", "访问成功");
		try {
			String jsonParams=HttpReqUtil.readStreamText(request.getInputStream(), "utf-8");
			testService.testDemon(jsonParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//解析参数
		
		return map;
	}
	

}
