package com.eyeota.codingfun.cache;

import java.util.HashMap;
import java.util.List;

public class TestLookupCache {
	public static void main(String[] args) {
		
		LookupCache cache = new Lookup("/Users/traqy/github/eyeota-coding-project/src/test/resources/shortdata.json");
		
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "paramName1", "paramVal6");
		for ( int i=0; i< cf1.length; i++){
			System.out.println(cf1[i]);
		}		
	}
}
