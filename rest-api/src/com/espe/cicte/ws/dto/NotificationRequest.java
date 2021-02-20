package com.espe.cicte.ws.dto;

public class NotificationRequest extends BaseRequest {
    
	/**
	 * Attribute that represent a channel (for example. 8 )
	 */
	private int channel;
	
	private int id;
	
	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "NotificationRequest [channel=" + channel + ", id=" + id + "]";
	}
	
	
}
