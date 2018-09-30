package com.YunGrocer.servlet;
import java.util.List;


import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import com.YunGrocer.javabeans.Product;
import com.YunGrocer.service.ProductServiceImpl;
import com.opensymphony.xwork2.Action;

/**
 * FindByPriceRange.java
 * @author anyunpei
 * 2018��9��25������9:11:27
 * ���ݼ۸����������Ʒ
 */
public class FindByPriceRange implements Action  {
	
	private String productName;
	private Integer currentPage;
	private Double lowPrice;
	private Double highPrice;
	List<Product> products;


	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		Integer results=0;
		try {
			products = new ProductServiceImpl().queryByPriceRange(productName, currentPage, lowPrice, highPrice); 
			results = new ProductServiceImpl().queryByPriceRangeCount(productName,lowPrice, highPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("�����Ϊ"+results);
		request.setAttribute("pages", (int) Math.ceil(1.0 * results / 3));
		return "findByPriceRangeSuccess";
	}

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


}
