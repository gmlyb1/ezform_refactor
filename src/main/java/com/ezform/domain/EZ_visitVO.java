package com.ezform.domain;

import java.sql.Date;

public class EZ_visitVO {
	
	private int id;
	private Date visit_date;
	private String ip_address;
	private String user_agent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	@Override
	public String toString() {
		return "EZ_visitVO [id=" + id + ", visit_date=" + visit_date + ", ip_address=" + ip_address + ", user_agent="
				+ user_agent + "]";
	}
	
	
}
