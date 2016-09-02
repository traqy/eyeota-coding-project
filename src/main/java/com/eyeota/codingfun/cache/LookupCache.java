package com.eyeota.codingfun.cache;

import java.nio.file.Files;
import java.nio.file.FileSystemNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.*;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import  org.codehaus.jettison.json.JSONArray;

/**
 * Interface to be implemented by your solution
 */
public interface LookupCache {
    public SegmentConfig[] getSegmentFor(final String orgKey, final String paramKey);
    public SegmentConfig[] getSegmentFor(final String orgKey, final String paramKey, final String paramValKey);
}

class Lookup implements LookupCache {
	
	public Lookup(final String jsonFile){
		
		String jsonConfigString = this.readFile(jsonFile);
		
		try{
			JSONArray jsonArrayFirstLevel = new JSONArray( jsonConfigString.trim() );
			for (int i = 0; i <  jsonArrayFirstLevel.length(); i++){
				//org1
				JSONObject jsonObjectFirstLevel = jsonArrayFirstLevel.getJSONObject(i);
				Iterator<String> keysFirstLevel = jsonObjectFirstLevel.keys();
	            while (keysFirstLevel.hasNext()) {
	                String kFirstLevel = keysFirstLevel.next().toString();
	                System.out.print(kFirstLevel + ":");
	                JSONArray jsonArraySecondLevel = jsonObjectFirstLevel.getJSONArray(kFirstLevel);
	                for (int j = 0; j < jsonArraySecondLevel.length(); j++) {
	                	JSONObject jsonObjectSecondLevel = jsonArraySecondLevel.getJSONObject(j);
	                	Iterator<String> keysSecondLevel = jsonObjectSecondLevel.keys();
	                	while (keysSecondLevel.hasNext()){
	                		String kSecondLevel = keysSecondLevel.next().toString();
	                		System.out.print(kSecondLevel + ":");
	                		JSONArray jsonArrayThirdLevel = jsonObjectSecondLevel.getJSONArray(kSecondLevel);
	                		for (int k = 0; k < jsonArrayThirdLevel.length(); k++) {
	                			JSONObject jsonObjectThirdLevel = jsonArrayThirdLevel.getJSONObject(k);
	                			Iterator<String> keysThirdLevel = jsonObjectThirdLevel.keys();
	                			while (keysThirdLevel.hasNext()){
	                				String kThirdLevel = keysThirdLevel.next().toString();
	                				System.out.print(kThirdLevel);	                				
	                				JSONObject jsonObjectSegment = jsonObjectThirdLevel.getJSONObject(kThirdLevel);
	                				String segmentId = jsonObjectSegment.getString("segmentId");
	                				System.out.println(" --> " + segmentId);
	                			}
	                		}
	                	}
	                }
	                
	            }				
				System.out.print("debug");
			}
		}
		catch (JSONException je){
			je.printStackTrace();
		}
	}
	
	private String readFile(String filename) {
         File f = new File(filename);
         String retval = "";
         try {
             byte[] bytes = Files.readAllBytes(f.toPath());
             retval = new String(bytes,"UTF-8");
         } catch (FileSystemNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 return retval;
	}	
	public SegmentConfig[] getSegmentFor(final String orgKey, final String paramKey){
		 SegmentConfig[] sconfig = new SegmentConfig[] { };
		 return sconfig;
	}
	
	public SegmentConfig[] getSegmentFor(final String orgKey, final String paramKey, final String paramValKey){
		 SegmentConfig[] sconfig = new SegmentConfig[] { };
		 return sconfig;		
	}

	
}