package com.eyeota.codingfun.cache;

import com.eyeota.codingfun.cache.Lookup;

import static org.junit.Assert.*;

import org.junit.Test;

public class LookupTest {

	String jsonFile = "/Users/traqy/github/eyeota-coding-project/src/test/resources/shortdata.json";	
	LookupCache cache = new Lookup(jsonFile);

	@Test
	public void test1() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "paramName1");

		assertArrayEquals(cf1, new SegmentConfig[] {} );
	}

	@Test
	public void test2() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "paramName1", "paramVal1");

		assertEquals(cf1[0].getSegmentId(),"seg_1234");
		assertEquals(cf1.length,1);
	}

	@Test
	public void test3() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "paramName1", "paramVal2");
		assertEquals(cf1[0].getSegmentId(),"intr.edu");
		assertEquals(cf1.length,1);
		
		SegmentConfig[] cf2 = cache.getSegmentFor("org1", "paramName1", "paramVal3");
		assertEquals(cf2[0].getSegmentId(),"intr.edu");
		assertEquals(cf2.length,1);
		
		SegmentConfig[] cf3 = cache.getSegmentFor("org1", "paramName1", "paramVal4");
		assertEquals(cf3[0].getSegmentId(),"intr.edu");
		assertEquals(cf3.length,1);
		
		SegmentConfig[] cf4 = cache.getSegmentFor("org1", "paramName1", "paramVal5");
		assertEquals(cf4[0].getSegmentId(),"intr.edu");
		assertEquals(cf4.length,1);
		
		
	}

	@Test
	public void test4() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "paramName1", "paramVal6");

		assertEquals(cf1.length,3);

		assertEquals(cf1[0].getSegmentId(),"dem.infg.m");
		assertEquals(cf1[1].getSegmentId(),"intr.heal");
		assertEquals(cf1[2].getSegmentId(),"dem.infg.f");
		
	}

	@Test
	public void test5a() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "testedu" );

		assertEquals(cf1.length,1);

		assertEquals(cf1[0].getSegmentId(),"n277");		
	}	
	
	@Test
	public void test5b() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "testedu", "");

		assertEquals(cf1.length,1);

		assertEquals(cf1[0].getSegmentId(),"n277");		
	}

	@Test
	public void test5c() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "testedu", "whatever");
		assertArrayEquals(cf1, new SegmentConfig[] {} );		
	}	

	@Test
	public void test6a() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "gen", "Male");
		assertEquals(cf1.length,1);
		assertEquals(cf1[0].getSegmentId(),"dem.g.m");		

	}	
	@Test
	public void test6b() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "gen", "Female");
		assertEquals(cf1.length,1);
		assertEquals(cf1[0].getSegmentId(),"dem.g.f");		

	}	
	
	@Test
	public void test6c() {
		SegmentConfig[] cf1 = cache.getSegmentFor("org1", "gen", "Whatever value other than Male or Female");
		assertArrayEquals(cf1, new SegmentConfig[] {} );		
	}	

}
