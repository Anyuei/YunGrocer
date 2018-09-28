package com.YunGrocer.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.YunGrocer.javabeans.YGUser;
import com.YunGrocer.service.UserServiceImpl;
import com.opensymphony.xwork2.Action;

import utils.MD5Utils;

public class Register implements Action{
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String encryptpassword = new MD5Utils().getStringMD5(password);
		String realname = request.getParameter("realname");
		String zip = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String avatarPath = request.getParameter("avatarPath");
		String tel = request.getParameter("tel");
		//如果用户名已经存在，重定向去注册页面
		if (new UserServiceImpl().findByName(username)!= null) {
			request.getSession().setAttribute("registerError", "用户名已被使用");
			return "RegisterFail";
		} else {//不存在的时候注册新的用户
			new UserServiceImpl().regist(new YGUser(username, encryptpassword, realname, Integer.parseInt(zip), address,avatarPath,tel));
			session.setAttribute("username", username);
			return "RegisterSuccess";
		}
	}
}
