package com.YunGrocer.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.YunGrocer.javabeans.YGUser;
import com.YunGrocer.service.UserServiceImpl;
import com.opensymphony.xwork2.Action;

public class Register implements Action{
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String zip = request.getParameter("zipcode");
		String address = request.getParameter("address");
		//����û����Ѿ����ڣ��ض���ȥע��ҳ��
		if (new UserServiceImpl().findByName(username)!= null) {
			request.getSession().setAttribute("registerError", "�û����ѱ�ʹ��");
			return "RegisterFail";
		} else {//�����ڵ�ʱ��ע���µ��û�
			new UserServiceImpl().regist(new YGUser(username, password, realname, Integer.parseInt(zip), address));
			session.setAttribute("usernameLog", username);
			return "RegisterSuccess";
		}
	}
}