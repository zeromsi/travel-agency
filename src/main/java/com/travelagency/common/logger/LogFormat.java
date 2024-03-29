package com.travelagency.common.logger;

import java.util.Date;
import java.util.UUID;

public class LogFormat {
	private String id;
	private Date timestamp;
	private String className;
	private String methodName;
	private String result;
	private String parameters;
	private String userName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LogFormat(String className, String methodName, String result, String parameters, String userName) {
		super();
		this.id = UUID.randomUUID().toString();
		this.timestamp = new Date();
		this.className = className;
		this.methodName = methodName;
		this.result = result;
		this.parameters = parameters;
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "LogFormat [id=" + id + ", timestamp=" + timestamp + ", className=" + className + ", methodName="
				+ methodName + ", result=" + result + ", parameters=" + parameters + ", userName=" + userName + "]";
	}

	
	
}
