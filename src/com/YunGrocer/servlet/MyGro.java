package com.YunGrocer.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.YunGrocer.dao.UserDaoImpl;
import com.YunGrocer.javabeans.YGUser;
import com.opensymphony.xwork2.ActionSupport;



public class MyGro extends ActionSupport{
	private String username;
	private String address;
	private String tel;
	private Integer zip;
	public String myGroInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		String username = (String) request.getSession().getAttribute("username");
		YGUser user=userDaoImpl.queryByUsername(username);
		request.setAttribute("username", user.getUsername());
		request.setAttribute("name", user.getName());
		request.setAttribute("zip", user.getZip());
		request.setAttribute("address", user.getAddress());
		request.setAttribute("tel", user.getTel());
		return "myGro";
	}
	public String modifyMyGro() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		String username = (String) request.getSession().getAttribute("username");
		YGUser user=userDaoImpl.queryByUsername(username);
		userDaoImpl.updateUser(
				new YGUser(username, 
						user.getPassword(), 
						user.getName(),
						zip, 
						address, 
						user.getAvatarPath(), 
						tel)
				);
		request.setAttribute("username", user.getUsername());
		request.setAttribute("name", user.getName());
		request.setAttribute("address", user.getAddress());
		request.setAttribute("tel", user.getTel());
		return "myGro";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	

}
