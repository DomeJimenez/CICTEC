package com.espe.cicte.ws.dto;

public class DeviceFinger extends BaseResponse{
	
	private boolean status;
	private String serialNumber;
	private String brightness;
	private String contrast;
	private String deviceId;
	private String version;
	private String gain;
	private String dpi;
	private String height;
	private String width;
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getBrightness() {
		return brightness;
	}
	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}
	public String getContrast() {
		return contrast;
	}
	public void setContrast(String contrast) {
		this.contrast = contrast;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getGain() {
		return gain;
	}
	public void setGain(String gain) {
		this.gain = gain;
	}
	public String getDpi() {
		return dpi;
	}
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	@Override
	public String toString() {
		return "deviceFinger [status=" + status + ", serialNumber=" + serialNumber + ", brightness=" + brightness
				+ ", contrast=" + contrast + ", deviceId=" + deviceId + ", version=" + version + ", gain=" + gain
				+ ", dpi=" + dpi + ", height=" + height + ", width=" + width + "]";
	}
    
	
	
}
