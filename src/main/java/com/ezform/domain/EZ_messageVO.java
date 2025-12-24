package com.ezform.domain;

import java.sql.Date;

public class EZ_messageVO {
	
	private int ms_seq;
	private String ms_title;
	private String receiver_name;
	private int gubun;
	private Date create_date;
	private String ms_content;
	private String sender_name;
	private String user_id;
	private int read_yn;
	public int getMs_seq() {
		return ms_seq;
	}
	public void setMs_seq(int ms_seq) {
		this.ms_seq = ms_seq;
	}
	public String getMs_title() {
		return ms_title;
	}
	public void setMs_title(String ms_title) {
		this.ms_title = ms_title;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public int getGubun() {
		return gubun;
	}
	public void setGubun(int gubun) {
		this.gubun = gubun;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getMs_content() {
		return ms_content;
	}
	public void setMs_content(String ms_content) {
		this.ms_content = ms_content;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getRead_yn() {
		return read_yn;
	}
	public void setRead_yn(int read_yn) {
		this.read_yn = read_yn;
	}
	@Override
	public String toString() {
		return "EZ_messageVO [ms_seq=" + ms_seq + ", ms_title=" + ms_title + ", receiver_name=" + receiver_name
				+ ", gubun=" + gubun + ", create_date=" + create_date + ", ms_content=" + ms_content + ", sender_name="
				+ sender_name + ", user_id=" + user_id + ", read_yn=" + read_yn + "]";
	}
	
	
	
}
