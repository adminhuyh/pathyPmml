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

public class TestPredict {
	public static Map lrHeartInputMap = new HashMap();
	public static  PMML pmml;
	static  String path = "D:/workfile/python - copy/model.pmml";
	static  String  []  ss;
	static  ModelEvaluatorFactory modelEvaluatorFactory;
	static  ModelEvaluator<?> modelEvaluator;
	static  Evaluator evaluator;
	static List<InputField> inputFields;
	static{
		       try {
		        	long begin = System.currentTimeMillis();
		        	File file = new File(path);
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
		
		
		/*train_x feature list:
	    ['user_id', 'pos_id', 'ad_id', 'time_stamp', 'page', 'network', 'app_category_c1', 'rating']*/
		
		/*
"815802|93|321|1555849780|4|3|9|6",
"231775|93|285|1555813161|4|2|10|7",
"556553|173|129|1555850836|4|3|13|8",
"556162|69|36|1555826954|2|2|1|10",
"395922|93|419|1555860395|4|3|5|10",
"77278|94|322|1556194871|7|2|6|9",
"46244|2|127|1555846981|3|3|3|7",
"958827|93|284|1555815696|4|2|13|7",
"1981744|92|292|1556106690|4|3|3|8",
"697586|2|143|1555835596|3|3|6|6",
"284978|2|295|1555826865|3|3|8|9",
"424998|94|351|1555841092|7|3|5|9",
"480731|92|294|1555802818|4|2|3|7",
"78309|94|453|1556105305|7|0|3|8",
"433166|93|85|1555819967|4|3|13|4",
"166975|2|90|1555841672|3|3|3|5",
"655826|178|628|1555843781|9|3|12|10",
"348505|92|371|1555829974|4|2|9|9",
"207651|174|211|1555851378|7|3|0|9",
"994287|93|472|1555849810|4|2|14|7",
*/
       ss = new String []{
    		   "N2YxZTI0N2Q3NWQ1M2M0MzQ4YzQ2YmI3ZGU5ZjU0NDhjM2NiZDg1NmQwMzQ2MzZmMDkzMTIyODk4ODUyYjcyYw==|351|31690|1561473879|Keyword|4g|在线视频|9",
    		   "ZmI4MDBjZDcwNDhkM2RjMDIwZThiYzRiYjMxMGMwZmYyMGQxNzNhYzcwODAwM2IzODMyOWFmMWY2YjYxM2E5Mg==|351|31871|1561461589|Keyword|WiFi|学习教育|9",
    		   "MWM0ZWJjMTg3YzdlOGZmYWU0NWQyNTE1ZmQ4M2IxMTlmOTkwMGMzNjcwMDYyNzAxN2UzMGMwNWM3ZmUwMGIzMw==|4|31621|1561427933|HOME|WiFi|新闻资讯|9",
    		   "MDg5N2IzYjExZmI5ZGY3MmUzMWNkNGQ2MGEzYmE2MWM0YzVkM2MxNzlkN2E1MGVlZGI4ZDg2YWMwZWQzN2FjOQ==|4|31618|1561468683|HOME|4g|在线视频|7",
    		   "MTY2NzM5ODIwNWU4NmNhMzA3YmMwZTIyYjFmZjM3Mzg5ZDc4ZmM2YjBhYmRiNGRjMTVjODI2NmNiYzFkZTkwMg==|57|31790|1561396416|HOME|WiFi|旅游出行|6",
    		   "M2UzMGU1MDNmZGRjZTI0OGEyNDlhODQwMTcyOTM4ODcwNjI1MGFiZjY1NTVkY2M2OTdiYzA1Mzc0ODFkNzk2Zg==|4|31615|1561423945|HOME|WiFi|在线视频|9",
    		   "OTAwZTA5ZDk3YjhmOWQ4ZDU2MDFkOTQxMzg4MTFlYTJmOTliMDA1ODIyMzE2OTk0M2Y3NDg3ZDNiNmNhYmIyNA==|107|29063|1561440600|Featured|4g|在线视频|5",
    		   "ZWExNDE0ZWFkZjI2MjI4OTZlOTBjZDg5MDA1MDI2NDk3YjllMGRkNjBjMDBmYzk2ZWI0NjFiMTZkZDAzNmVjYQ==|4|31633|1561396833|HOME|WiFi|汽车服务|9",
    		   "YjVkMDViMmE5YWEzODgwMjRiOTMyOTNiNGFkMDAwZmY1MTAyMTQ5YjU3Y2RkNDgyOTlkNTFkNzFlODgxODFjNA==|4|31494|1561441915|HOME|4g|音乐音频|4",
    		   "NGE1MmQyMWI0YzJjMzU0ODcxOTY2OWVkMWE2NzA1YWI1ZWRmMmMyNTY0NTNjMDFhMDZiODg2NTEzYzI2MTM4YQ==|162|25776|1561407518|SearchResult|4g|旅游出行|6",
    		   "ZjBjZWQxZTU4MWQ4MjJmMmQ2NWU3ODZiMTQ4ZTVjOThkNjMxNGYzYzg3OWQzZTBjNzdjNDc2M2JjYWZmYmNiZA==|157|26679|1561464508|Keyword|4g|电子商务|7",
    		   "MDIxYmZjMDI2MTExYmViZTFjMDAwMDU3YWI0NWQ3ZjZjNTZmY2U3ZGEzYTkyM2VlMTkxNDExOGUyMGFhZTZiZA==|4|32170|1561426770|HOME|WiFi|旅游出行|10",
    		   "MzgzZWJmNzZmZGNjNjE0YTU2MDg3MjE4YjA5ZWM1OGM3ZmI1YTk3MWQ0Yzg2NjAwMjQ3ZDA2ZTQ2YjFmNTc1NQ==|162|36768|1561460068|SearchResult|WiFi|在线视频|0",
    		   "N2UxZmMxOTEyOTRkN2M2YzlhNTcyMmUyNzFkNjE1ZTNjZmFiM2VhOGIzMGM5NTVmZmU4Y2E4NDg3ZjkzY2Q5Mg==|351|32254|1561417906|Keyword|4g|电子商务|6",
    		   "ZjA5MzAwZjBjMWE2ODNhYjk3NGNkNGM0Zjc0ZThjMmM2YTgzZWE5ZmUxNGExZmU0YzNkZTc2YWRjOTA3ZWJiYw==|4|31999|1561430572|HOME|WiFi|新闻资讯|9",
    		   "MzRmZGNjOTdjNTcyZjk3MTJiOTY0ZGM5NDcwZWNkOTZlYTFjMTgwZmQ1YmQ5MWRhMDg4YWE2ZTg1ZjhiYjZiOA==|162|36869|1561458808|SearchResult|WiFi|学习教育|8",
    		   "NThiNDAyZWJmZjMwMTFjYzkzYjg1MzllODQzOGQ3Y2VhM2MwZTJiM2IzZWM5YjM5ZjMzMTc2NDlhZmU1ODAzMQ==|350|31473|1561440555|Keyword|4g|游戏|5",
    		   "YzFjNWZhOGMzZTZkZGRhMTM3ZGI1Zjg3OTk1NjI3Yjc3NGE5YjI4NzMxMThjODlmMTExMjk1ZTFmM2M0NDM5YQ==|4|31932|1561429105|HOME|4g|旅游出行|9",
    		   "ZmIzOWUxY2RhOWJhYmQwZWY3ZGViOGQ5OGJkNmM1YWU3YWY0MWZlY2VhNTlmZjkyMjFkMzIzNmEwYTg5MTYwZA==|158|30292|1561436368|Keyword|4g|电子商务|7",
    		   "OTcxMDUyODRhMDU1NmExMjM3ZWU0M2ExOTI1OThkNjU5NDMwYmFlNzc4MTI2MjQxYjA2NzAwZjk5ODliYzY5Zg==|157|29943|1561438635|Keyword|WiFi|音乐音频|8"
       };
		//准备画像数据－key和原始特征一致即可   231775|93|285|1555813161|4|2|10|7
	}
	public static void main(String[] args) throws Exception {
		/*String kk  []  = ss[0].split("\\|",-1);
		for(int ii = 0 ;ii<kk.length;ii++){
			System.out.println("kk="+kk[ii]);
		}*/
		String kk [] ;
	    for(int i = 0;i<ss.length;i++){
	    	
	    	
	    	{
	    		kk = ss[i].split("\\|",-1);
		    	lrHeartInputMap.clear();
		    	lrHeartInputMap.put("user_id",kk[0]);
				lrHeartInputMap.put("pos_id", kk[1]);
				lrHeartInputMap.put("ad_id", kk[2]);
				lrHeartInputMap.put("time_stamp", kk[3]);
				lrHeartInputMap.put("page", kk[4]);
				lrHeartInputMap.put("network",kk[5]);
				lrHeartInputMap.put("app_category_c1", kk[6]);
				lrHeartInputMap.put("rating", kk[7]);		      
		        try{
		        	predictLrHeart(i);
		        }catch(Exception e){
		        	System.out.println("this is error");
		        }
	    	}
	    	
	    }
	}
	//预测核心代码
	public  static void predictLrHeart(int k) throws Exception {
	    //模型导入
		  System.out.println("index="+k+"       ");
	        
	      /*  System.out.println("evaluator="+evaluator);*/
	       
/*	        System.out.print("inputFields="+inputFields+"         ");
*/	   
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
