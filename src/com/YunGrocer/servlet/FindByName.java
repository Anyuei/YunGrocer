package com.YunGrocer.servlet;
import java.util.List;


import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import com.YunGrocer.javabeans.Product;
import com.YunGrocer.service.ProductServiceImpl;
import com.opensymphony.xwork2.Action;


public class FindByName implements Action  {
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Product> products = null;
		try {
			products = new ProductServiceImpl().findProduct(1,
					request.getParameter("searchContent"), null,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pages", Math.ceil(1.0*products.size()/3));
		request.setAttribute("products", products);
		return "findByNameSuccess";
	}

}
