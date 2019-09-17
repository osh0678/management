package com.management.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_pw")
public class UserPw {

	@Id
	@Column(updatable=false)
	private String userNo;		//유저 키값
	private String userPw;		//유저 비밀번호
	
	@Column(updatable=false)
	private String regUser;		//등록 유저
	
	@Column(updatable=false)
	private String regDt;		//등록일
	
	
	private String uptUser;		//수정 유저
	private String uptDt;		//수정일
	
	private String lastPw;		//마지막 패스워드
	private String lastDt;		//마지막 변경일
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
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
	public String getLastPw() {
		return lastPw;
	}
	public void setLastPw(String lastPw) {
		this.lastPw = lastPw;
	}
	public String getLastDt() {
		return lastDt;
	}
	public void setLastDt(String lastDt) {
		this.lastDt = lastDt;
	}
	
}
