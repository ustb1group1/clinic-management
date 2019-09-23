package com.ust.model;

import java.sql.Date;

public class DoctorAppointment {
	
	private int appId;
	private int regId;
	private int dId;
	private String sName;
	private String dateOfApp;
	private String consultStatus;
	private String pFName;
	private String pLName;
	
	
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
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
	public String getpFName() {
		return pFName;
	}
	public void setpFName(String pFName) {
		this.pFName = pFName;
	}
	public String getpLName() {
		return pLName;
	}
	public void setpLName(String pLName) {
		this.pLName = pLName;
	}
	
	
	
	

}
