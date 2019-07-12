package org.jpmml.evaluator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.evaluator.ProbabilityDistribution;
import org.jpmml.evaluator.TargetField;
import org.jpmml.evaluator.Value;

public class Predict {
	
	String path = "";
	String  []  ss;
	ModelEvaluatorFactory modelEvaluatorFactory;
	ModelEvaluator<?> modelEvaluator;
	Evaluator evaluator;
	List<InputField> inputFields;
	
	public Predict(){
		
		
	}
	public Predict(ModelEvaluatorFactory modelEvaluatorFactory,ModelEvaluator modelEvaluator,
			       Evaluator evaluator,List<InputField> inputFields){
		this.inputFields=inputFields;
		this.evaluator=evaluator;
		this.modelEvaluatorFactory = modelEvaluatorFactory;
		this.modelEvaluator=modelEvaluator;
	}
	
	
	
	
	//预测核心代码
	public  void predictLrHeart(Map  lrHeartInputMap) throws Exception {
	    //模型导入	
            try{
                //过模型的原始特征，从画像中获取数据，作为模型输入
        		Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
            	FieldName inputFieldName=null;
            	 FieldValue inputFieldValue = null;
            	 Object rawValue  =null;
        	        for (InputField inputField : inputFields) {
        	            inputFieldName = inputField.getName();
        	            rawValue = lrHeartInputMap.get(inputFieldName.getValue());      	           
        	            inputFieldValue = inputField.prepare(rawValue);
        	            arguments.put(inputFieldName, inputFieldValue);
        	        }
               Map<FieldName, ?> results = evaluator.evaluate(arguments);
     	       List<TargetField> targetFields = evaluator.getTargetFields();
     	        //获得结果，作为回归预测的例子，只有一个输出。对于分类问题等有多个输出。
     	       FieldName targetFieldName=null;
     	       Object targetFieldValue =null;
     	       ProbabilityDistribution  newtargetFieldValue  =null;
     	       List<Map.Entry<String, Value<Double>>> list =null;
     	        for (TargetField targetField : targetFields) {
     	            targetFieldName = targetField.getName();
     	            targetFieldValue = results.get(targetFieldName);
     	            newtargetFieldValue = (ProbabilityDistribution) targetFieldValue;
     	            list = newtargetFieldValue.getWinnerRanking();
     	            Entry e = null;
     	            for(int i=0;i<list.size();i++){
     	            Entry<String, Value<Double>> entry = list.get(i);
     	               if(("0").equals (entry.getKey())){list.remove(i);}
     	            }
                     System.out.println("target: " + targetFieldName.getValue() + " value: " + list.get(0).toString());
     	        }            	
            }catch(Exception e){
            	 e.printStackTrace();
            	throw e;
            }

	}
}
