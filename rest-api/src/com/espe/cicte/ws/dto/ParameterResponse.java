package com.espe.cicte.ws.dto;
/**
 * This class allow to set parameters from Database. For example native currencyId
 * @author djarrin
 *
 */
public class ParameterResponse extends BaseResponse{
	/**
	* Attribute that represent the name of the parameter
    */
	private String parameterName;
	/**
	* Attribute that represent the value of the parameter
    */
	private String parameterValue;
	
	/**
	* Getter of parameter name 
	* @return The value of ParameterName 
    */	
	public String getParameterName() {
		return parameterName;
	}
	
	/**
	* Setter of ParameterName
    * @param parameterName The value of setParameterName
	*/
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	/**
	* Getter of parameter value 
	* @return The value of ParameterValue 
    */	
	public String getParameterValue() {
		return parameterValue;
	}
	
	/**
	* Setter of ParameterValue
	* @param parameterName The value of parameterValue
	*/
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	

}
