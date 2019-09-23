package com.ust.model;

import java.util.Date;

public class Prescription {
	
	private int prescId;
	private int dId;
	private int regId;
	private int medId;
	private String medFreq;
	private int medDays;
	private Date prescDate;
	private String pharmacyStatus;
	private String medName;
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
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	public int getPrescId() {
		return prescId;
	}
	public void setPrescId(int prescId) {
		this.prescId = prescId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
	}
	public int getMedId() {
		return medId;
	}
	public void setMedId(int medId) {
		this.medId = medId;
	}
	public String getMedFreq() {
		return medFreq;
	}
	public void setMedFreq(String medFreq) {
		this.medFreq = medFreq;
	}
	public int getMedDays() {
		return medDays;
	}
	public void setMedDays(int medDays) {
		this.medDays = medDays;
	}
	
	public Date getPrescDate() {
		return prescDate;
	}
	public void setPrescDate(Date prescDate) {
		this.prescDate = prescDate;
	}
	public String getPharmacyStatus() {
		return pharmacyStatus;
	}
	public void setPharmacyStatus(String pharmacyStatus) {
		this.pharmacyStatus = pharmacyStatus;
	}
	
	

}
