package com.management.mng;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cmm_Info")
public class CmmInfo {
	
	@Id
	@Column(updatable=false)
	private String userNo;       //고객키값
	
	@Column(updatable=false)
	private String userName;     //고객 이름
	
	@Column(updatable=false)
	private String phoneId;      //핸드폰번호
	
	private String telecomType;  //통신사
	
	@Column(updatable=false)
	private String userSeq;      //주민번호
	
	private String creditRating; //신용등급
	private String workType;     //직장구분
	private String workName;     //직장명
	private String workYn;       //재직유무
	private String carYn;        //자가유무
	private String houseType;    //주거종류
	private String salary;       //연봉
	private String location;     //지역
	
	@Column(updatable=false)
	private String regUser;      //등록한 계정
	
	@Column(updatable=false)
	private String regDt;        //등록한 일시
	
	private String uptUser;      //업데이트한 계정
	private String uptDt;        //업데이트한 일시
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	public String getTelecomType() {
		return telecomType;
	}
	public void setTelecomType(String telecomType) {
		this.telecomType = telecomType;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getWorkYn() {
		return workYn;
	}
	public void setWorkYn(String workYn) {
		this.workYn = workYn;
	}
	public String getCarYn() {
		return carYn;
	}
	public void setCarYn(String carYn) {
		this.carYn = carYn;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUptUser() {
		return uptUser;
	}
	public void setUptUser(String uptUser) {
		this.uptUser = uptUser;
	}
	public String getUptDt() {
		return uptDt;
	}
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
	
}
