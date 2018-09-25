package com.YunGrocer.service;

import java.util.List;

import com.YunGrocer.javabeans.Product;


public interface ProductService {
	List<Product> findProduct(Integer currentPage
			,String productName,String lessOrGreat,Double price);
	/**
	 * 根据商品id查询
	 * @param productId
	 * @return
	 */
	Product findById(Integer productId);
	/**
	 * 根据当前页面 查询某价格区间内的商品
	 * @param productName 
	 * @param currentPage
	 * @param lowPrice
	 * @param highPrice
	 * @return
	 */
	List<Product> queryByPriceRange(String productName,Integer currentPage,Double lowPrice,Double highPrice);
	/**
	 * 计算某个价格区间的 商品数
	 * @param lowPrice
	 * @param highPrice
	 * @return
	 */
	Integer queryByPriceRangeCount(String productName,Double lowPrice,Double highPrice);
}
