package com.ezform.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class EZ_inqVO {
	
	private int rowNo;
	private int inq_no;
	private String inq_title;
	private String inq_content;
	private String inq_name;
	private String inq_dname;
	private String em_email;
	private String inq_file;
	private MultipartFile uploadFile;
	private Date inq_regdate;
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public int getInq_no() {
		return inq_no;
	}
	public void setInq_no(int inq_no) {
		this.inq_no = inq_no;
	}
	public String getInq_title() {
		return inq_title;
	}
	public void setInq_title(String inq_title) {
		this.inq_title = inq_title;
	}
	public String getInq_content() {
		return inq_content;
	}
	public void setInq_content(String inq_content) {
		this.inq_content = inq_content;
	}
	public String getInq_name() {
		return inq_name;
	}
	public void setInq_name(String inq_name) {
		this.inq_name = inq_name;
	}
	public String getInq_dname() {
		return inq_dname;
	}
	public void setInq_dname(String inq_dname) {
		this.inq_dname = inq_dname;
	}
	public String getEm_email() {
		return em_email;
	}
	public void setEm_email(String em_email) {
		this.em_email = em_email;
	}
	public String getInq_file() {
		return inq_file;
	}
	public void setInq_file(String inq_file) {
		this.inq_file = inq_file;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Date getInq_regdate() {
		return inq_regdate;
	}
	public void setInq_regdate(Date inq_regdate) {
		this.inq_regdate = inq_regdate;
	}
	@Override
	public String toString() {
		return "EZ_inqVO [rowNo=" + rowNo + ", inq_no=" + inq_no + ", inq_title=" + inq_title + ", inq_content="
				+ inq_content + ", inq_name=" + inq_name + ", inq_dname=" + inq_dname + ", em_email=" + em_email
				+ ", inq_file=" + inq_file + ", uploadFile=" + uploadFile + ", inq_regdate=" + inq_regdate + "]";
	}

	
	
	
	
}
