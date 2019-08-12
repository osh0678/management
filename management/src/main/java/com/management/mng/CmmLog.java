package com.management.mng;

import javax.persistence.Entity;

import org.springframework.stereotype.Controller;

@Controller
@Entity
public class CmmLog {

	private String userNo;
	private String userName;
	private String logDt;
	private String retryCall;
	private String rmk;
	private String existCash;
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
	public String getLogDt() {
		return logDt;
	}
	public void setLogDt(String logDt) {
		this.logDt = logDt;
	}
	public String getRetryCall() {
		return retryCall;
	}
	public void setRetryCall(String retryCall) {
		this.retryCall = retryCall;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	public String getExistCash() {
		return existCash;
	}
	public void setExistCash(String existCash) {
		this.existCash = existCash;
	}
	
	
}
