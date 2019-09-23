package com.ust.model;

import java.sql.Date;

public class Staff {
 private int sId;
 private int roleId;
 private String roleName;
 private String sName;
 private Date DOB;
 private String sGender;
 private String sAddr;
 private String sExp;
 private String sPhNo;
 private String sEmail;
 private String username;
 private String password;
 private String isActive;
 private Date createdDate;
 
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public int getsId() {
	return sId;
}
public void setsId(int sId) {
	this.sId = sId;
}
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}

public String getsName() {
	return sName;
}
public void setsName(String sName) {
	this.sName = sName;
}

public String getsGender() {
	return sGender;
}
public void setsGender(String sGender) {
	this.sGender = sGender;
}
public String getsAddr() {
	return sAddr;
}
public void setsAddr(String sAddr) {
	this.sAddr = sAddr;
}
public String getsExp() {
	return sExp;
}
public void setsExp(String sExp) {
	this.sExp = sExp;
}
public String getsPhNo() {
	return sPhNo;
}
public void setsPhNo(String sPhNo) {
	this.sPhNo = sPhNo;
}
public String getsEmail() {
	return sEmail;
}
public void setsEmail(String sEmail) {
	this.sEmail = sEmail;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getIsActive() {
	return isActive;
}
public void setIsActive(String isActive) {
	this.isActive = isActive;
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
public Staff() {
	super();
	// TODO Auto-generated constructor stub
}
 
}
