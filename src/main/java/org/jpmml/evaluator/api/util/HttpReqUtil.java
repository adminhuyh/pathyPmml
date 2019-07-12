package org.jpmml.evaluator.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.json.GsonJsonParser;

import net.sf.json.JSONObject;



/**
 * 
 * @Title: SendUtil.java
 * @Package com.cheil.pengtai.api.common.util
 * @ClassName: SendUtil
 * @Description: (调用接口通用方法)
 * @author:huyh
 * @date 2019年4月28日 下午2:24:39
 */
public class HttpReqUtil {
    public static final String APPLICATION_JSON = "application/json";
    public static final String TEXT_XML="text/xml";
	
	public static String sendToUrl(String sendtext, String urll, String encoding,String type) throws Exception {
		return sendToUrl(sendtext, urll, encoding, 10,type);
	}
	
	
	public static String sendToUrl(String sendtext, String urll,String type) throws Exception {
		return sendToUrl(sendtext, urll, "utf-8", 10,type);
	}
	
	public static String sendToUrl(String urll,String type) throws Exception {
		return sendToUrl("", urll, "utf-8", 10,type);
	}
	
	public static String sendToUrl(String sendtext, String urll,Integer seconds,String type) throws Exception {
		return sendToUrl(sendtext, urll, "utf-8", seconds,type);
	}

	
	public static String sendToUrl(String sendtext, String urll, String encoding, Integer seconds,String type) throws Exception {
		URL url = new URL(urll);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		int httpTimeOut = seconds * 1000;
		conn.setConnectTimeout(httpTimeOut);
		conn.setReadTimeout(httpTimeOut);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		if(StringUtils.isNotBlank(type)){
			conn.setRequestProperty("Content-Type", type);						
		}else{
			conn.setRequestProperty("Content-Type", "text/xml");			
		}
		conn.setRequestProperty("Accept-Charset", encoding);
		conn.setRequestMethod("POST");
		conn.connect();
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encoding));
			output.write(sendtext);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.flush();
				output.close();
			}
		}

		return readStreamText(conn.getInputStream(), encoding);
	}

	public static String readStreamText(InputStream stream, String encoding) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(stream, encoding));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return sb.toString();
	}
	public static void main(String[] args) {
	
	}
}
