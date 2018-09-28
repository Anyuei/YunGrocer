package com.YunGrocer.javabeans;

import java.io.Serializable;

public class YGUser implements Serializable{
	private String username;
	private String password;
	private String name; 
	private Integer zip; // ” ±‡
	private String address;
	private String avatarPath;//Õ∑œÒÕº∆¨µÿ÷∑
	private String tel;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toString() {
		return "YGUser [username=" + username + ", password=" + password + ", name=" + name + ", zip=" + zip
				+ ", address=" + address + ", avatarPath=" + avatarPath + ", tel=" + tel + "]";
	}
	public YGUser(String username, String password, String name, Integer zip, String address, String avatarPath,
			String tel) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.zip = zip;
		this.address = address;
		this.avatarPath = avatarPath;
		this.tel = tel;
	}
	public YGUser() {
		super();
		// TODO Auto-generated constructor stub
	}
}
