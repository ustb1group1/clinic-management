package com.ust.model;

import java.util.Date;

public class AssignLabTest {
	private int regId;
	private int dId;
	private int labId;
	private String pFName;
	private int assignLabId;
	private Date assigLabDate;
	private String sampleStatus;
	private String testStatus;
	private String lName;
	private String sName;
	
	
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getAssignLabId() {
		return assignLabId;
	}
	public void setAssignLabId(int assignLabId) {
		this.assignLabId = assignLabId;
	}
	public Date getAssigLabDate() {
		return assigLabDate;
	}
	public void setAssigLabDate(Date assigLabDate) {
		this.assigLabDate = assigLabDate;
	}
	public String getpFName() {
		return pFName;
	}
	public void setpFName(String pFName) {
		this.pFName = pFName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
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
	public int getLabId() {
		return labId;
	}
	public void setLabId(int labId) {
		this.labId = labId;
	}
	
	public String getSampleStatus() {
		return sampleStatus;
	}
	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	
}
