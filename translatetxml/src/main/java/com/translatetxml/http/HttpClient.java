package com.translatetxml.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.client.utils.URIBuilder;

public class HttpClient {
	
	URL url;
	String baseUrl ;
    HttpURLConnection conn;
    StringBuilder string;
    
    public HttpClient(String baseUrl){
    	this.baseUrl = baseUrl;
        try {
			this.url = new URL(this.baseUrl);
	        this.conn = (HttpURLConnection) this.url.openConnection();
		} catch (IOException e) {
            System.out.println("Failed to open connection. Error Message: " + e.getMessage()); 
			e.printStackTrace();  
			System.exit(1);
		}
    }

    public HttpClient(String baseUrl, HashMap<String, String> parameters) {
    	this.baseUrl = baseUrl;
    	setParameters(baseUrl, parameters);
    	try {
	        this.url = new URL(this.baseUrl);
	        this.conn = (HttpURLConnection) this.url.openConnection();
		} catch (IOException e) {
	        System.out.println("Failed to open connection. Error Message: " + e.getMessage()); 
			e.printStackTrace(); 
			System.exit(1);
		}
    }
	
    public void setHeaders(HashMap<String, String> headersMap) {
        if (headersMap != null && !headersMap.isEmpty()) {
            for (HashMap.Entry<String, String> map : headersMap.entrySet()) {
                conn.setRequestProperty(map.getKey(), map.getValue());
            }
        }
    }
    
    private void setParameters(String baseUrl, HashMap<String, String> parameters) {
		try {
			URIBuilder builder = new URIBuilder(baseUrl);
			
			if (parameters != null) {				
				parameters.forEach((key,value) -> builder.addParameter(key, value));

				this.baseUrl = builder.toString();
			}
		} catch (URISyntaxException e) {
            System.out.println("Failed to build url query parameters. Error Message: " + e.getMessage()); 
			e.printStackTrace();
			System.exit(1);
        }
    }

    public void setRequest(RequestMethod requestType) throws Exception {
        switch (requestType) {
            case GET:
                conn.setRequestMethod("GET");
                break;
            case POST:
                conn.setRequestMethod("POST");
                break;
            case PUT:
                conn.setRequestMethod("PUT");
                break;
            case DELETE:
                conn.setRequestMethod("DELETE");
                break;
            case HEAD:
                conn.setRequestMethod("HEAD");
                break;
            case PATCH:
                conn.setRequestMethod("PATCH");
                break;
        }
    }
    
    public void setReadTimeout(int settingValue) {
        conn.setReadTimeout(settingValue);
    }
    
    public void setConnectTimeout(int settingValue) {
        conn.setConnectTimeout(settingValue);
    }
    
    public void setConnectionInput(Boolean settingValue) {
        conn.setDoInput(settingValue);
    }
    
    public void setConnectionOutput(Boolean settingValue) {
        conn.setDoOutput(settingValue);
    }

    public String getServerResponse(){
        StringBuilder stringBuilder = new StringBuilder();
        
        try {
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    String line;
			    while ((line = reader.readLine()) != null) {
			    	if(!(line.equals("null"))) {
			    		if(!(line.matches("\\d+"))) {
				            stringBuilder.append(line);
			    		} else {
			    			int apiResponseCode = 0;
			    			try {
			    				apiResponseCode = Integer.parseInt(line.trim());
			    				ResponseCodeEnum responseCode = ResponseCodeEnum.getResponseCodeEnum(apiResponseCode);
			    				if(responseCode != null && responseCode != ResponseCodeEnum.SUCCESSFUL) {
			    					throw new Exception("Bad API Response: \n " + "Response Message: " 
			    							+ responseCode.getDesc() + "\n Response Code: " 
			    							+ responseCode.getCode());
			    				}
			    			} catch (NumberFormatException e) {
			    	            System.out.println(apiResponseCode + " is not a valid integer number"); 
			    				e.printStackTrace();
			    			} catch (Exception e) {
			    	            System.out.println(e.getMessage()); 
			    				e.printStackTrace();
							}
			    		}
			    	}
			    }
			    reader.close();
			    return stringBuilder.toString();
			} else {
			    return "Bad Response : \n Message: \t" 
			    		+ conn.getResponseMessage() 
			    		+ "\n Code: \t" + conn.getResponseCode();
			}
		} catch (IOException e) {
            System.out.println(e.getMessage()); 
			e.printStackTrace();
			System.exit(1);
		}
        
		return stringBuilder.toString();
    }
}
