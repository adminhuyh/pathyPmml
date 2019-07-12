package org.jpmml.evaluator.api.service;

import java.util.Map;

import org.jpmml.evaluator.Predict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class TestService {
   	
   
   
   public void testDemon(String jsonParams){
	    JSONObject map = new JSONObject();
		try{
			 map = JSONObject.fromObject(jsonParams);
		}catch (Exception e) {
		}
		
		//json对象解析
   }
}
