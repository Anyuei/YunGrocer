package com.YunGrocer.service;

import java.util.List;

import com.YunGrocer.javabeans.Product;


public interface ProductService {
	List<Product> findProduct(Integer currentPage
			,String productName,String lessOrGreat,Double price);
	/**
	 * ������Ʒid��ѯ
	 * @param productId
	 * @return
	 */
	Product findById(Integer productId);
	/**
	 * ���ݵ�ǰҳ�� ��ѯĳ�۸������ڵ���Ʒ
	 * @param productName 
	 * @param currentPage
	 * @param lowPrice
	 * @param highPrice
	 * @return
	 */
	List<Product> queryByPriceRange(String productName,Integer currentPage,Double lowPrice,Double highPrice);
	/**
	 * ����ĳ���۸������ ��Ʒ��
	 * @param lowPrice
	 * @param highPrice
	 * @return
	 */
	Integer queryByPriceRangeCount(String productName,Double lowPrice,Double highPrice);
}
