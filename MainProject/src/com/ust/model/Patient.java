package com.ust.model;

import java.sql.Date;

public class Patient {
	
	private int regId;
	private String pFName;
	private String pLName;
	private String pGender;
	private Date DOB;
	private String pAddr;
	private String pPhNo;
	private String pBloodGrp;
	private Date createdDate;
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
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

	public String getpGender() {
		return pGender;
	}

	public void setpGender(String pGender) {
		this.pGender = pGender;
	}

	

	public String getpAddr() {
		return pAddr;
	}

	public void setpAddr(String pAddr) {
		this.pAddr = pAddr;
	}

	public String getpPhNo() {
		return pPhNo;
	}

	public void setpPhNo(String pPhNo) {
		this.pPhNo = pPhNo;
	}

	public String getpBloodGrp() {
		return pBloodGrp;
	}

	public void setpBloodGrp(String pBloodGrp) {
		this.pBloodGrp = pBloodGrp;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	
	
	
	
	

}
