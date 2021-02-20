package com.espe.cicte.ws.dto;


/**
 * This class has information about products
 * @author GQU
 *
 */

public class Notification {
	/**
	 * Attribute that represent a message (for example. Payment of your basic service is available.)
	 */
	private String message;
	/**
	 * Attribute that represent a Delivery Date (for example. 2016-12-31)
	 */
	private String deliveryDate;
	
	@Override
	public String toString() {
		return "Notification [message=" + message
				+ ", deliveryDate=" + deliveryDate + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}