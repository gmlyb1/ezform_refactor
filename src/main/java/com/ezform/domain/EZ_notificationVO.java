package com.ezform.domain;

import java.security.Timestamp;

public class EZ_notificationVO {

	private int id;
	private String user_id;
	private String type;
	private String content;
	private String link;
	private boolean is_read;
	private Timestamp create_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public boolean isIs_read() {
		return is_read;
	}
	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "EZ_notificationVO [id=" + id + ", user_id=" + user_id + ", type=" + type + ", content=" + content
				+ ", link=" + link + ", is_read=" + is_read + ", create_date=" + create_date + "]";
	}
	
	
}
