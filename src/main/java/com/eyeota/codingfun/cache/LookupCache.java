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
    public HashMap<String, HashMap<String, HashMap<String, List<SegmentConfig>>>> getConfig();
}

class Lookup implements LookupCache {
	
	private HashMap<String, HashMap<String, HashMap<String, List<SegmentConfig>>>> config = new HashMap<String, HashMap<String, HashMap<String, List<SegmentConfig>>>>();
	
	public Lookup(final String jsonFile){
		
		String jsonConfigString = this.readFile(jsonFile);
		
		
		try{
			JSONArray jsonArrayFirstLevel = new JSONArray( jsonConfigString.trim() );
			for (int i = 0; i <  jsonArrayFirstLevel.length(); i++){
				//org1
				JSONObject jsonObjectFirstLevel = jsonArrayFirstLevel.getJSONObject(i);
				@SuppressWarnings("unchecked")
				Iterator<String> keysFirstLevel = jsonObjectFirstLevel.keys();
	            while (keysFirstLevel.hasNext()) {
	                String kFirstLevel = keysFirstLevel.next().toString();
	                
            		HashMap<String, HashMap<String, List<SegmentConfig>>> secondHashMap = new HashMap<String, HashMap<String, List<SegmentConfig>>>();

	                JSONArray jsonArraySecondLevel = jsonObjectFirstLevel.getJSONArray(kFirstLevel);
	                for (int j = 0; j < jsonArraySecondLevel.length(); j++) {
	                	JSONObject jsonObjectSecondLevel = jsonArraySecondLevel.getJSONObject(j);
	                	@SuppressWarnings("unchecked")
						Iterator<String> keysSecondLevel = jsonObjectSecondLevel.keys();
	                	while (keysSecondLevel.hasNext()){
	                		String kSecondLevel = keysSecondLevel.next().toString();
	                		JSONArray jsonArrayThirdLevel = jsonObjectSecondLevel.getJSONArray(kSecondLevel);
	                		
            				HashMap<String, List<SegmentConfig>> firstHashMap = new HashMap<String, List<SegmentConfig>>();

	                		for (int k = 0; k < jsonArrayThirdLevel.length(); k++) {
	                			JSONObject jsonObjectThirdLevel = jsonArrayThirdLevel.getJSONObject(k);
	                			@SuppressWarnings("unchecked")
								Iterator<String> keysThirdLevel = jsonObjectThirdLevel.keys();
	                			while (keysThirdLevel.hasNext()){
	                				String kThirdLevel = keysThirdLevel.next().toString();
	                				JSONObject jsonObjectSegment = jsonObjectThirdLevel.getJSONObject(kThirdLevel);
	                				String segmentId = jsonObjectSegment.getString("segmentId");
	                				
	                				String[] finalKeysThirdLevel = kThirdLevel.split("\\n");
	                				for (String finakKThirdLevel: finalKeysThirdLevel ) {
	                					
	                					if ( finakKThirdLevel.equals("paramVal6") ) {
		                	                System.out.print(kFirstLevel + ":");
		        	                		System.out.print(kSecondLevel + ":");
			                				System.out.println(finakKThirdLevel + " --> " + segmentId);	                						
	                					}
		                				
		                				
		                				//List<SegmentConfig> segmentConfigArray = this.getSegmentConfigArray(kFirstLevel,kSecondLevel,finakKThirdLevel);		                				
		                				List<SegmentConfig> segmentConfigArray = firstHashMap.get(finakKThirdLevel);
		                				if ( segmentConfigArray != null ) {
		                					segmentConfigArray.add( new SegmentConfig(segmentId) );
		                					firstHashMap.put(finakKThirdLevel, segmentConfigArray);
		                				}
		                				else{
			                				List<SegmentConfig> segmentConfigArrayNew = new ArrayList<SegmentConfig>();
			                				segmentConfigArrayNew.add(new SegmentConfig(segmentId));
			                				firstHashMap.put(finakKThirdLevel, segmentConfigArrayNew);		                					
		                				}		                				
		                				
		                						                						                				
	                				}	                						                				
	                			}
                				secondHashMap.put(kSecondLevel, firstHashMap);		                				
	                		}
	                	}
	                
	                	this.config.put(kFirstLevel, secondHashMap);
	                }                
	            }				
			}
		}
		catch (JSONException je){
			je.printStackTrace();
		}
	}
	
	public HashMap<String, HashMap<String, HashMap<String, List<SegmentConfig>>>> getConfig(){
		return this.config;
	}
	
	private List<SegmentConfig> getSegmentConfigArray(final String k1, final String k2, final String k3) {
		
		try{
			
			HashMap<String, HashMap<String, List<SegmentConfig>>> firstMap = this.config.get(k1);
			if ( firstMap != null){
				HashMap<String, List<SegmentConfig>> secondMap = firstMap.get(k2);
				if ( secondMap !=null) {
					List<SegmentConfig> segmentConfigArray = secondMap.get(k3);
					if ( segmentConfigArray != null ) {
						return segmentConfigArray;
					}
					else{
						return null;
					}
				}
				else{
					return null;
				}				
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			return null;
		}		
	}

	public SegmentConfig[] getSegmentFor(final String k1, final String k2, final String k3){
		
		SegmentConfig[] segmentConfigArray = new SegmentConfig[] { };
		try{
			
			HashMap<String, HashMap<String, List<SegmentConfig>>> firstMap = this.config.get(k1);
			if ( firstMap != null){
				HashMap<String, List<SegmentConfig>> secondMap = firstMap.get(k2);
				if ( secondMap !=null) {
					
					List<SegmentConfig> segmentConfigArrayList = secondMap.get(k3);
					if ( segmentConfigArrayList != null ) {
						int array_size = segmentConfigArrayList.size();
						segmentConfigArray = new SegmentConfig[array_size];
						
						for (int i = 0 ; i < segmentConfigArrayList.size() ; i++) {
							SegmentConfig segmentConfig = segmentConfigArrayList.get(i);
							segmentConfigArray[i] = segmentConfig;
							//return segmentConfigArray;
						}						
					}
				}
			}
		}
		catch(Exception e){
		}
		return segmentConfigArray;
	}

	public SegmentConfig[] getSegmentFor(final String k1, final String k2){		
		return this.getSegmentFor(k1, k2, "");
	}
	
	public SegmentConfig[] getSegmentForOld(final String k1, final String k2, final String k3){
		
		List<SegmentConfig> segmentConfigList = this.getSegmentConfigArray(k1, k2, k3);
				
		if ( segmentConfigList != null ){
			int sizeOfSegmentConfigList = segmentConfigList.size();
			SegmentConfig[] segmentConfigArray = new SegmentConfig[sizeOfSegmentConfigList];
			for (int i = 0 ; i < sizeOfSegmentConfigList ; i++) {
				SegmentConfig segmentConfig = segmentConfigList.get(i);
				segmentConfigArray[i] = segmentConfig;
			}
			return segmentConfigArray;
		}
		else{
			return null;
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
	
}