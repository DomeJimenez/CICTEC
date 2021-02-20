/**
 * This class model is used such a DTO for Notifications
 */
package com.espe.cicte.ws.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aliger
 * @date 10/11/2016
 */
public class NotifyRequest extends BaseRequest{
	
	/**
	 * The id card for the user (Eg.1785454645)
	 */
	private String identityCard;
	/**
	 * The option filter (Eg. OT)
	 */
	private String optionFilter;
	/**
	 * OTP for the user
	 */
	private String token;
	/**
	 * The beneficiary bank in the transfer
	 */
	private String benefBank;
	/**
	 * Debit account
	 */
	private String debitAccount;
	/**
	 * Credit account
	 */
	private String creditAccount;
	/**
	 * The Amount
	 */
	private double ammount;
	/**
	 * Mail to notify
	 */
	private String mail;
	/**
	 * The name of the Beneficiary
	 */
	private String nameBenef;
	

	/**
	 * @return the identityCard
	 */
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * @param identityCard the identityCard to set
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	/**
	 * @return the optionFilter
	 */
	public String getOptionFilter() {
		return optionFilter;
	}

	/**
	 * @param optionFilter the optionFilter to set
	 */
	public void setOptionFilter(String optionFilter) {
		this.optionFilter = optionFilter;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the benefBank
	 */
	public String getBenefBank() {
		return benefBank;
	}

	/**
	 * @param benefBank the benefBank to set
	 */
	public void setBenefBank(String benefBank) {
		this.benefBank = benefBank;
	}

	/**
	 * @return the debitAccount
	 */
	public String getDebitAccount() {
		return debitAccount;
	}

	/**
	 * @param debitAccount the debitAccount to set
	 */
	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}

	/**
	 * @return the creditAccount
	 */
	public String getCreditAccount() {
		return creditAccount;
	}

	/**
	 * @param creditAccount the creditAccount to set
	 */
	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	/**
	 * @return the ammount
	 */
	public Double getAmmount() {
		return ammount;
	}

	/**
	 * @param ammount the ammount to set
	 */
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the nameBenef
	 */
	public String getNameBenef() {
		return nameBenef;
	}

	/**
	 * @param nameBenef the nameBenef to set
	 */
	public void setNameBenef(String nameBenef) {
		this.nameBenef = nameBenef;
	}	
	
	public Map<Object, Object> getObjectsValues()
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		Field[] fields = this.getClass().getDeclaredFields();
		//Object[] myObjects = new Object[fields.length];
		//map.put(fields[0].getName(), identityCard);
		//map.put(fields[1].getName(), optionFilter);
		//map.put(fields[2].getName(), token);
		map.put(fields[3].getName(), benefBank);
		map.put(fields[4].getName(), debitAccount);
		map.put(fields[5].getName(), creditAccount);
		map.put(fields[6].getName(), ammount);
		map.put(fields[7].getName(), mail);
		map.put(fields[8].getName(), nameBenef);
		
//		myObjects[0]=identityCard;
//		myObjects[1]=optionFilter;
//		myObjects[2]=token;
//		//In this there are not needed validation it controls in the implementation
//		myObjects[3]=benefBank;
//		myObjects[4]=debitAccount;
//		myObjects[5]=creditAccount;
//		myObjects[6]=ammount;
//		myObjects[7]=mail;
//		myObjects[8]=nameBenef;
//		
		return map;
	}
	

}
