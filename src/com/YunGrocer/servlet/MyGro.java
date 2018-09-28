package com.YunGrocer.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.YunGrocer.dao.UserDaoImpl;
import com.YunGrocer.javabeans.YGUser;
import com.opensymphony.xwork2.ActionSupport;



public class MyGro extends ActionSupport{
	public String myGroInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		String username = (String) request.getSession().getAttribute("username");
		YGUser user=userDaoImpl.queryByUsername(username);
		request.setAttribute("username", user.getUsername());
		request.setAttribute("name", user.getName());
		request.setAttribute("address", user.getAddress());
		request.setAttribute("tel", user.getTel());
		return "myGro";
	}

}
