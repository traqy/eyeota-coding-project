package com.eyeota.codingfun.cache;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LookupTestData {

	LookupCache cache = new Lookup();

	@Test
	public void test1() {
		SegmentConfig[] cf1 = cache.getSegmentFor("6lkb2cv", "Edu", "high_school");
		assertEquals(cf1[0].getSegmentId(),"intr.edu.scho");
	}
	
	@Test
	public void test2() {
		SegmentConfig[] cf1 = cache.getSegmentFor("6lkb2cv", "Edu", "bachelors");
		assertEquals(cf1[0].getSegmentId(),"intr.edu");
	}

	@Test
	public void test3() {
		SegmentConfig[] cf2 = cache.getSegmentFor("6lkb2cv", "Edu", "graduate");
		assertEquals(cf2[0].getSegmentId(),"intr.edu");
	}
	
}