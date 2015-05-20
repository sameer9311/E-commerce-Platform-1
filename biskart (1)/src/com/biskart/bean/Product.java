package com.biskart.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Product {
	private int pid;
	private String name;
	private String category;
	private String subcategory;
	private double rating;
	private boolean is_verified;

	private String imgUrl;
	public Product(int pid,String name,String category,String subcategory,double rating,boolean is_verified) {
		this.pid = pid;
		this.name = name;
		this.category = category;
		this.subcategory = subcategory;
		this.rating = rating;
		this.is_verified = is_verified;
	}
	public boolean is_verified() {
		return is_verified;
	}
	public void set_verified(boolean is_verified) {
		this.is_verified = is_verified;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String toString() {
		return this.getName();
		
	}
}