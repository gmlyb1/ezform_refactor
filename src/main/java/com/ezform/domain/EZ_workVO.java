package com.ezform.domain;

import java.sql.Timestamp;

public class EZ_workVO {
	private int em_id;
	private Timestamp work_regdate;
	private String work_status;
	private String user_email;
	private int work_status_cnt;
	
	
	
	public int getWork_status_cnt() {
		return work_status_cnt;
	}
	public void setWork_status_cnt(int work_status_cnt) {
		this.work_status_cnt = work_status_cnt;
	}
	private String em_email;
	
	public String getEm_email() {
		return em_email;
	}
	public void setEm_email(String em_email) {
		this.em_email = em_email;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getEm_id() {
		return em_id;
	}
	public void setEm_id(int em_id) {
		this.em_id = em_id;
	}
	public Timestamp getWork_regdate() {
		return work_regdate;
	}
	public void setWork_regdate(Timestamp work_regdate) {
		this.work_regdate = work_regdate;
	}
	public String getWork_status() {
		return work_status;
	}
	public void setWork_status(String work_status) {
		this.work_status = work_status;
	}
	@Override
	public String toString() {
		return "EZ_workVO [em_id=" + em_id + ", work_regdate=" + work_regdate + ", work_status=" + work_status
				+ ", user_email=" + user_email + ", work_status_cnt=" + work_status_cnt + ", em_email=" + em_email
				+ "]";
	}

	
}
