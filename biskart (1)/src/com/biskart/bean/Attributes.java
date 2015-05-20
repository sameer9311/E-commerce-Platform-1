package com.biskart.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Attributes {

	private int aid;
	private String aname;
	private String avalue;
	
	public Attributes(int aid, String aname)
	{
		this.aid = aid;
		this.aname = aname;
		
	}
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAvalue() {
		return avalue;
	}
	public void setAvalue(String avalue) {
		this.avalue = avalue;
	}
	public String toString() {
		return this.getAname();
		
	}
	
	
}
