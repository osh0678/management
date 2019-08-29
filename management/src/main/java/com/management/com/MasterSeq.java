package com.management.com;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="master_seq")
public class MasterSeq {
	
	@Id
	private String seqName;     //시퀀스 이름
	
	private String seqCnt;   //시퀀스 카운트

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getSeqCnt() {
		return seqCnt;
	}

	public void setSeqCnt(String seqCnt) {
		this.seqCnt = seqCnt;
	}
	
	

}
