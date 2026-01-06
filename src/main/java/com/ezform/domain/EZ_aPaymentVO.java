package com.ezform.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EZ_aPaymentVO {

	private int ap_id; // 결재 ID
	private String user_id; // 신청자 ID (사원 테이블 FK)
	private String ap_type; // 결재 종류 (ex: 연차, 반차 등)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ap_start_date; // 휴가 시작일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ap_end_date; // 휴가 종료일
	private String ap_reason; // 휴가 사유
	private String ap_status; // 결재 상태 (대기/승인/반려)
	private Date ap_create_date; // 생성일
	private Date ap_update_date; // 수정일
	private String approver_id; // 결재자 ID
	public int getAp_id() {
		return ap_id;
	}
	public void setAp_id(int ap_id) {
		this.ap_id = ap_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAp_type() {
		return ap_type;
	}
	public void setAp_type(String ap_type) {
		this.ap_type = ap_type;
	}
	public Date getAp_start_date() {
		return ap_start_date;
	}
	public void setAp_start_date(Date ap_start_date) {
		this.ap_start_date = ap_start_date;
	}
	public Date getAp_end_date() {
		return ap_end_date;
	}
	public void setAp_end_date(Date ap_end_date) {
		this.ap_end_date = ap_end_date;
	}
	public String getAp_reason() {
		return ap_reason;
	}
	public void setAp_reason(String ap_reason) {
		this.ap_reason = ap_reason;
	}
	public String getAp_status() {
		return ap_status;
	}
	public void setAp_status(String ap_status) {
		this.ap_status = ap_status;
	}
	public Date getAp_create_date() {
		return ap_create_date;
	}
	public void setAp_create_date(Date ap_create_date) {
		this.ap_create_date = ap_create_date;
	}
	public Date getAp_update_date() {
		return ap_update_date;
	}
	public void setAp_update_date(Date ap_update_date) {
		this.ap_update_date = ap_update_date;
	}
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	@Override
	public String toString() {
		return "EZ_aPaymentVO [ap_id=" + ap_id + ", user_id=" + user_id + ", ap_type=" + ap_type + ", ap_start_date="
				+ ap_start_date + ", ap_end_date=" + ap_end_date + ", ap_reason=" + ap_reason + ", ap_status="
				+ ap_status + ", ap_create_date=" + ap_create_date + ", ap_update_date=" + ap_update_date
				+ ", approver_id=" + approver_id + "]";
	}

	
	
	
}
