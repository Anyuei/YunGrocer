package com.YunGrocer.servlet;

import java.io.IOException;
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

import utils.MD5Utils;

/**
 * Servlet implementation class Login
 */

public class Login implements Action {
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		if (session == null) {
			return "loginFail";
		} else {
			// 获取验证码
			String kaptcha = request.getParameter("kaptcha");
			// 获取用户名
			String username = request.getParameter("username");
			// 获取密码
			String password = request.getParameter("password");
			String encryptpassword = new MD5Utils().getStringMD5(password);
			System.out.println(encryptpassword);
			// 获取是否记住用户名和密码
			String rememberMe = request.getParameter("rememberMe");

			// 调用service层login方法
			YGUser user = new UserServiceImpl().login(username, encryptpassword);
			// 判断用户名密码是否正确，或验证码是否正确
			if (user == null || !session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(kaptcha)) {
				return "loginFail";
			} else {
				// 登录成功且记住我被选中时，记住用户名和密码

				if (rememberMe != null) {
					Cookie usernameCookie = new Cookie("username", URLEncoder.encode(username, "UTF-8"));
					Cookie passwordCookie = new Cookie("password", password);
					usernameCookie.setMaxAge(60 * 60 * 24);
					passwordCookie.setMaxAge(60 * 60 * 24);
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				} else {
					
					Cookie usernameCookie = new Cookie("username", URLEncoder.encode(username, "UTF-8"));
					Cookie passwordCookie = new Cookie("password", password);
					usernameCookie.setMaxAge(0);
					passwordCookie.setMaxAge(0);
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				}
				// 设置分页，每页显示数量
				int pageContain = 3;
				Cookie usernameWelcome = new Cookie("usernameWelcome", username);
				response.addCookie(usernameWelcome);
				session.setAttribute("usernameLog", username);
				// 设置当前页面号
				session.setAttribute("currentPage", "1");
				session.setAttribute("flag", session);
				return "loginSuccess";

			}

		}

	}
}
