package com.ezform.domain;

import java.security.Timestamp;
import java.util.Date;

public class EZ_notificationVO {

	private int noti_id;
	private String receiver_name;
	private String noti_type;
	private String noti_message;
	private String noti_link;
	private boolean is_read;
	private Date create_date;
	public int getNoti_id() {
		return noti_id;
	}
	public void setNoti_id(int noti_id) {
		this.noti_id = noti_id;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getNoti_type() {
		return noti_type;
	}
	public void setNoti_type(String noti_type) {
		this.noti_type = noti_type;
	}
	public String getNoti_message() {
		return noti_message;
	}
	public void setNoti_message(String noti_message) {
		this.noti_message = noti_message;
	}
	public String getNoti_link() {
		return noti_link;
	}
	public void setNoti_link(String noti_link) {
		this.noti_link = noti_link;
	}
	public boolean isIs_read() {
		return is_read;
	}
	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "EZ_notificationVO [noti_id=" + noti_id + ", receiver_name=" + receiver_name + ", noti_type=" + noti_type
				+ ", noti_message=" + noti_message + ", noti_link=" + noti_link + ", is_read=" + is_read
				+ ", create_date=" + create_date + "]";
	}
	
	
	
}
