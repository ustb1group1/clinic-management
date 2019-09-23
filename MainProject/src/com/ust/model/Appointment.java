package com.ust.model;

public class Appointment {
	
	private int appId;
	private int regId;
	private int dId;
	private String dateOfApp;
	private String consultStatus;
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public String getDateOfApp() {
		return dateOfApp;
	}
	public void setDateOfApp(String dateOfApp) {
		this.dateOfApp = dateOfApp;
	}
	public String getConsultStatus() {
		return consultStatus;
	}
	public void setConsultStatus(String consultStatus) {
		this.consultStatus = consultStatus;
	}
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
