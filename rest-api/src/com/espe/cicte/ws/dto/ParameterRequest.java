package com.espe.cicte.ws.dto;

/**
 * This class allow to get parameters from Database. For example native currencyId
 * @author djarrin
 *
 */
public class ParameterRequest extends BaseRequest{
	/**
	* Attribute that represent the name of the parameter
    */
	private String parameterName;
	
	/**
	* Attribute that represent the abbreviation name of the source's parameter
    */
	private String sourceAbbreviation;
	
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
	* Getter of Source Abbreviation
	* @return The value of SourceAbbreviation 
	*/
	public String getSourceAbbreviation() {
		return sourceAbbreviation;
	}
	
	/**
	* Setter of SourceAbbreviation
	* @param sourceAbbreviation The value of sourceAbbreviation
	*/
	public void setSourceAbbreviation(String sourceAbbreviation) {
		this.sourceAbbreviation = sourceAbbreviation;
	}

	
}
