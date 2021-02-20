package com.espe.cicte.ws.dto;

/**
 * Class used to retrieve Notification
 * @author GQU
 *
 */
public class NotificationResponse extends BaseResponse {
	
	/**
     * Attribute that represent a Array of New information
     */
	private Notification[] notificationsArray;

	public Notification[] getNotificationsArray() {
		return notificationsArray;
	}

	public void setNotificationsArray(Notification[] notificationsArray) {
		this.notificationsArray = notificationsArray;
	}

}