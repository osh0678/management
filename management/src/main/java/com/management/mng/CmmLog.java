package com.management.mng;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cmm_log")
public class CmmLog {
	
	@Id
	private String userNo;     //고객 키값
	
	private String userName;   //고객이름
	private String logDt;      //로그 이력
	private String retryCall;  //재콜
	private String rmk;        //내용
	private String existCash;  //부채
	private int logNo;		   //로그 번호
	
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
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	
}
