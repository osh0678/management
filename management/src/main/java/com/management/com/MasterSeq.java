package com.management.com;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="master_seq")
public class MasterSeq {
	
	@Id
	private String seqName;     //시퀀스 이름
	
	private int seqCnt;   //시퀀스 카운트

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public int getSeqCnt() {
		return seqCnt;
	}

	public void setSeqCnt(int cmmSeq) {
		this.seqCnt = cmmSeq;
	}
	
	

}
