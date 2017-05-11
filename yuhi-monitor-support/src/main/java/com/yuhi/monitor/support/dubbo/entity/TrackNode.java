package com.yuhi.monitor.support.dubbo.entity;

public class TrackNode {
	
	private String TrackId;
	private Long loadTimeMillis;
	private boolean isSuccess;
	private String ApiName;
	private String method;
	private String Arguments;
	private String Result;
	public String getTrackId() {
		return TrackId;
	}
	public void setTrackId(String trackId) {
		TrackId = trackId;
	}
	public Long getLoadTimeMillis() {
		return loadTimeMillis;
	}
	public void setLoadTimeMillis(Long loadTimeMillis) {
		this.loadTimeMillis = loadTimeMillis;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getApiName() {
		return ApiName;
	}
	public void setApiName(String apiName) {
		ApiName = apiName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getArguments() {
		return Arguments;
	}
	public void setArguments(String arguments) {
		Arguments = arguments;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
}
