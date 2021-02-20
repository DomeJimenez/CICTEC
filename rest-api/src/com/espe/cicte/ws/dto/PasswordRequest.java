package com.espe.cicte.ws.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PasswordRequest extends BaseRequest {

	private String channel;
	private String temporalPassword;
	private String password;
	private boolean definitivePassword;
	private String identityCardNumber;
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTemporalPassword() {
		return temporalPassword;
	}
	public void setTemporalPassword(String temporalPassword) {
		this.temporalPassword = temporalPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getDefinitivePassword() {
		return definitivePassword;
	}
	public void setDefinitivePassword(boolean definitivePassword) {
		this.definitivePassword = definitivePassword;
	}
	public String getIdentityCardNumber() {
		return identityCardNumber;
	}
	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}
	
	public Map<Object, Object> getObjectsValues()
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		Field[] fields = this.getClass().getDeclaredFields();
		map.put(fields[0].getName(),channel);
		//map.put(fields[1].getName(),temporalPassword);
		map.put(fields[2].getName(),password);
		//map.put(fields[3].getName(), definitivePassword);
		//map.put(fields[4].getName(),identityCardNumber);
			
		return map;
	}
}
