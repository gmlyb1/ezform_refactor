package com.ezform.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class EZ_materialVO {

	private int ma_seq;
	private String ma_dept;
	private String ma_userId;
	private String ma_title;
	private String ma_content;
	private String ma_file;
	private Date ma_regdate;
	private MultipartFile uploadFile;
	public int getMa_seq() {
		return ma_seq;
	}
	public void setMa_seq(int ma_seq) {
		this.ma_seq = ma_seq;
	}
	public String getMa_dept() {
		return ma_dept;
	}
	public void setMa_dept(String ma_dept) {
		this.ma_dept = ma_dept;
	}
	public String getMa_userId() {
		return ma_userId;
	}
	public void setMa_userId(String ma_userId) {
		this.ma_userId = ma_userId;
	}
	public String getMa_title() {
		return ma_title;
	}
	public void setMa_title(String ma_title) {
		this.ma_title = ma_title;
	}
	public String getMa_content() {
		return ma_content;
	}
	public void setMa_content(String ma_content) {
		this.ma_content = ma_content;
	}
	public String getMa_file() {
		return ma_file;
	}
	public void setMa_file(String ma_file) {
		this.ma_file = ma_file;
	}
	public Date getMa_regdate() {
		return ma_regdate;
	}
	public void setMa_regdate(Date ma_regdate) {
		this.ma_regdate = ma_regdate;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "EZ_materialVO [ma_seq=" + ma_seq + ", ma_dept=" + ma_dept + ", ma_userId=" + ma_userId + ", ma_title="
				+ ma_title + ", ma_content=" + ma_content + ", ma_file=" + ma_file + ", ma_regdate=" + ma_regdate
				+ ", uploadFile=" + uploadFile + "]";
	}

	
	
	
}
