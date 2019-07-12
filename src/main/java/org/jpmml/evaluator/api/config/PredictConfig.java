package org.jpmml.evaluator.api.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;


import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.evaluator.Predict;
import org.jpmml.evaluator.ProbabilityDistribution;
import org.jpmml.evaluator.TargetField;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class PredictConfig {
	static  PMML pmml;	
    static  String pmmlPath;
	private static Properties prop;
	static ModelEvaluatorFactory modelEvaluatorFactory;
	static ModelEvaluator<?> modelEvaluator;
	static Evaluator evaluator;
	static List<InputField> inputFields;
	static{
		    try {
		    	        if (prop == null) {
					        prop = new Properties();
		    	        }
		                InputStream inStream = PredictConfig.class
		        		      .getClassLoader().getResourceAsStream("application.properties");
		                //.getClassLoader().getResourceAsStream("application-stage.properties");
		                prop.load(inStream);
		            
		        	long begin = System.currentTimeMillis();
		        	pmmlPath=prop.getProperty("pmml.path");
		        	File file = new File(pmmlPath);
				    InputStream inputStream = new FileInputStream(file);
					pmml = org.jpmml.model.PMMLUtil.unmarshal(inputStream);
					long end = System.currentTimeMillis();
					System.out.println((end-begin)/1000+"秒");
					modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
				    modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
				    evaluator = (Evaluator) modelEvaluator;
				    inputFields = evaluator.getInputFields();
				} catch (Exception e) {
					e.printStackTrace();
				} 
	}
	@Bean
	public Predict initPredict(){
		Predict predict = new Predict(modelEvaluatorFactory,modelEvaluator,
				evaluator,inputFields);		
		return  predict;
		
	}
}
