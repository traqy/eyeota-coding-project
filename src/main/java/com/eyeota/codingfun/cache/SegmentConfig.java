package com.eyeota.codingfun.cache;

public class SegmentConfig {
	private String segmentId = "";
	
	public void SegmentId(final String paramSegmentId) {
		this.setSegmentId(paramSegmentId);
	}
	public void setSegmentId(final String val) {
		this.segmentId = val;
	}
	public String getSegmentId(){
		return this.segmentId;
	}
}
