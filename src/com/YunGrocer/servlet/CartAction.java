package com.YunGrocer.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.YunGrocer.javabeans.CartItem;
import com.YunGrocer.javabeans.Product;
import com.YunGrocer.service.ProductService;
import com.YunGrocer.service.ProductServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Integer[] productIds;
	private Integer currentPage;
	private String productName;
	private Double lowPrice;
	private Double highPrice;
	
	/**
	 * ���ﳵ���
	 * @return
	 */
	public String cartAdd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session =request.getSession();
		

		// ���Ҳ�Ʒ
		ProductService ps = new ProductServiceImpl();

		Product product = ps.findById(productId);
		// ��ȡ���ﳵ
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		CartItem cartItem = null;
		if (cart == null) {// �������ﳵ
			cart = new HashMap<Integer, CartItem>();
			cartItem = new CartItem(product, 1, product.getPrice() * 1);
			cart.put(productId, cartItem);
			session.setAttribute("cart", cart);
		} else {
			// �ж��Ƿ�����ӹ�����Ʒ
			if (cart.containsKey(productId)) {
				cartItem = cart.get(productId);
				cartItem.setAmount(cartItem.getAmount() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getAmount());
			} else {
				System.out.println("��Ʒ��Ϣ"+product);
				cartItem = new CartItem(product, 1, product.getPrice() * 1);
				cart.put(productId, cartItem);
			}
		}
		return "addSuccess";
	}
	/**
	 * ���ﳵɾ��
	 * @return
	 */
	public String cardDelete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(productIds!=null){
			// ��ȡ���ﳵ
			HttpSession session = request.getSession();
			Map<Integer,CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
			for(int i=0;i<productIds.length;i++){
				cart.remove(productIds[i]);
			}
		}
		return "deleteSuccess";
	}
	public String findAll(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("�ͻ���mac��ַΪ"+request.getRemoteAddr());
		System.out.println("�ͻ���ip��ַΪ"+request.getRemoteHost());
		
		Integer results=0;
		Integer pages=0;
		if (currentPage==null) {
			currentPage=1;
		}
		if (productName==null) {
			productName="";
		}
		
		List<Product> products = null;
/*		if (request.getAttribute("currentPage")==null) {                                                                                                         
			request.setAttribute("currentPage", 1);
		}*/
		try {
			products = new ProductServiceImpl().queryByPriceRange(productName, currentPage, lowPrice, highPrice);
			results = new ProductServiceImpl().queryByPriceRangeCount(productName,lowPrice, highPrice);
			System.out.println(request.getAttribute("currentPage"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(list.size());
		System.out.println(results);
		pages= (int) Math.ceil(1.0 * results / 3);
		System.out.println("findALL"+pages);
        request.setAttribute("pages",pages);
		request.setAttribute("products", products);
		return "findSuccess";
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer[] getProductIds() {
		return productIds;
	}
	public void setProductIds(Integer[] productIds) {
		this.productIds = productIds;
	}
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
