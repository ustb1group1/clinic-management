package com.ust.model;

import java.sql.Date;

public class DoctorObservation {
	private int dObsId;
	private String obserDate;
	private String obsNotes;
	private int regId;
	private int dId;
	private String pFName;
	private String sName;
	
	
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getpFName() {
		return pFName;
	}
	public void setpFName(String pFName) {
		this.pFName = pFName;
	}
	public int getdObsId() {
		return dObsId;
	}
	public void setdObsId(int dObsId) {
		this.dObsId = dObsId;
	}
	
	
	
	public String getObserDate() {
		return obserDate;
	}
	public void setObserDate(String obserDate) {
		this.obserDate = obserDate;
	}
	public String getObsNotes() {
		return obsNotes;
	}
	public void setObsNotes(String obsNotes) {
		this.obsNotes = obsNotes;
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
	public DoctorObservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
