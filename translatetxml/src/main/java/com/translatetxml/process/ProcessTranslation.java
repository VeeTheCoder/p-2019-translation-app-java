package com.translatetxml.process;

import com.translatetxml.utils.ConfigHandler;
import com.translatetxml.utils.xml.TxmlParser;
import com.translatetxml.utils.xml.TxmlWriter;
import com.translatetxml.http.HttpClient;
import com.translatetxml.http.RequestMethod;

import com.translatetxml.models.Translatable;

import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ProcessTranslation {
	
	private String sourceFile;
	private String outputFile;
	private String sourceLanguage;
	private String targetLanguage;
	private boolean isEvaluate;

	public ProcessTranslation(String sourceFile, String outputFile, String sourceLanguage, String targetLanguage, boolean isEvaluate) {
		this.sourceFile = sourceFile;
		this.outputFile = outputFile;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
		this.isEvaluate = isEvaluate;
	}
	
	public void execute() {
		HashMap<Integer, Translatable> hTabletranslatable = new HashMap<Integer,Translatable>();

		hTabletranslatable = parseSourceFile(sourceFile, hTabletranslatable);
		
		processTranslation(hTabletranslatable, sourceLanguage, targetLanguage);
		
	    generateOutputTxml(outputFile, isEvaluate, hTabletranslatable, sourceLanguage, targetLanguage);   
	}
	
	private void processTranslation(HashMap<Integer, Translatable> hTabletranslatable, String sourceLanguage, String targetLanguage) {
		hTabletranslatable.forEach((key,value) -> hTabletranslatable.get(key).setTarget(getTranslation(hTabletranslatable.get(key).getSource(), sourceLanguage, targetLanguage)));
	}

	private String getTranslation(String sourceSegement, String sourceLanguage, String targetLanguage) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		String sourceTranslation = "";

		parameters.put("wl_data", sourceSegement);
		parameters.put("wl_srclang", sourceLanguage);
		parameters.put("wl_trglang", targetLanguage);
		parameters.put("wl_password", new ConfigHandler("SECRET").getConfigValue());
		parameters.put("wl_errorstyle", new ConfigHandler("ERRORSTYLE").getConfigValue());
				
        try {
        	int readTimeout = Integer.parseInt(new ConfigHandler("READ_TIMEOUT").getConfigValue());
        	int connectTimeout = Integer.parseInt(new ConfigHandler("CONNECTION_TIMEOUT").getConfigValue());
        	
        	HttpClient requestHandler = new HttpClient(new ConfigHandler("BASE_URL_API").getConfigValue(), parameters);
			requestHandler.setRequest(RequestMethod.GET);
			requestHandler.setReadTimeout(readTimeout);
			requestHandler.setConnectTimeout(connectTimeout);
			requestHandler.setConnectionInput(true);
		    requestHandler.setConnectionOutput(true);
			sourceTranslation = requestHandler.getServerResponse();
		} catch (Exception e) {
			System.out.println(e.getMessage()+ "error");
			e.printStackTrace();
			System.exit(1);
		}
        
		return sourceTranslation;
	}

	private HashMap<Integer, Translatable> parseSourceFile(String path, HashMap<Integer, Translatable> hTabletranslatable) {
		TxmlParser parseTranslatable = new TxmlParser();
		
		 try {
			 	hTabletranslatable = parseTranslatable.translatableParser(path);            
	     } catch (ParserConfigurationException | SAXException e) {
	            System.out.println(e.getMessage());
				e.printStackTrace();
				System.exit(1);
	     }	
		 		 
		 return hTabletranslatable;
	}
	
	private void generateOutputTxml(String outputFile, Boolean isEvaluate, HashMap<Integer, Translatable> hTabletranslatable, String sourceTranslation, String targetTranslation) {
		TxmlWriter outputTxml = new TxmlWriter(outputFile,isEvaluate,hTabletranslatable,sourceTranslation,targetTranslation);
		outputTxml.writeTxml();
	}
}
