package com.YunGrocer.servlet;

import java.io.IOException;

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
			// ��ȡ��֤��
			String kaptcha = request.getParameter("kaptcha");
			// ��ȡ�û���
			String username = request.getParameter("username");
			// ��ȡ����
			String password = request.getParameter("password");
			// ��ȡ�Ƿ��ס�û���������
			String rememberMe = request.getParameter("rememberMe");

			// ����service��login����
			YGUser user = new UserServiceImpl().login(username, password);
			// �ж��û��������Ƿ���ȷ������֤���Ƿ���ȷ
			if (user == null || !session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(kaptcha)) {
				return "loginFail";
			} else {
				// ��¼�ɹ��Ҽ�ס�ұ�ѡ��ʱ����ס�û���������

				if (rememberMe != null) {
					Cookie usernameCookie = new Cookie("username", username);
					Cookie passwordCookie = new Cookie("password", password);
					usernameCookie.setMaxAge(60 * 60 * 24);
					passwordCookie.setMaxAge(60 * 60 * 24);
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				} else {
					Cookie usernameCookie = new Cookie("username", username);
					Cookie passwordCookie = new Cookie("password", password);
					usernameCookie.setMaxAge(0);
					passwordCookie.setMaxAge(0);
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				}
				// ���÷�ҳ��ÿҳ��ʾ����
				int pageContain = 3;
				Cookie usernameWelcome = new Cookie("usernameWelcome", username);
				response.addCookie(usernameWelcome);
				session.setAttribute("usernameLog", username);

				System.out.println(rememberMe + "00000");
				// ���õ�ǰҳ���
				session.setAttribute("currentPage", "1");
				// �����û�������
				session.setAttribute(username, password);
				session.setAttribute("flag", session);
				return "loginSuccess";

			}

		}

	}
}
