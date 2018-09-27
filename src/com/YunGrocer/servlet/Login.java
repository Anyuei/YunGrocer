package com.YunGrocer.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.YunGrocer.javabeans.YGUser;
import com.YunGrocer.service.UserServiceImpl;
import com.google.code.kaptcha.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import utils.MD5Utils;

/**
 * Servlet implementation class Login
 */

public class Login extends ActionSupport {

	private String kaptcha;
	private String username;
	private String password;
	private String rememberMe;
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String encryptpassword=null;
		if (session == null) {
			return "loginFail";
		} else {
			//加密密码
			System.out.println(kaptcha);
			encryptpassword = new MD5Utils().getStringMD5(password);
			// 调用service层login方法
			YGUser user = new UserServiceImpl().login(username, encryptpassword);
			System.out.println(user);
			// 判断用户名密码是否正确，或验证码是否正确
			if (user == null || !session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(kaptcha)) {
				return "loginFail";
			} else {
				// 登录成功且“记住我”被选中时，记住用户名和密码
				Cookie usernameCookie = null;
				try {
					usernameCookie = new Cookie("username", URLEncoder.encode(username, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Cookie passwordCookie = new Cookie("password", password);
				if (rememberMe != null) {
					usernameCookie.setMaxAge(60 * 60 * 24);
					passwordCookie.setMaxAge(60 * 60 * 24);
				} else {
					usernameCookie.setMaxAge(0);
					passwordCookie.setMaxAge(0);
				}
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
				// 设置当前页面号
				session.setAttribute("currentPage", "1");
				session.setAttribute("flag", session);
				session.setAttribute("username", username);
				return "loginSuccess";

			}

		}

	}
	public String getKaptcha() {
		return kaptcha;
	}
	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
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
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
}
