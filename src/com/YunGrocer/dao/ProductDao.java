package com.YunGrocer.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.YunGrocer.javabeans.Product;
/**
 * ProductDao.java
 * @author anyunpei
 * 2018年9月23日下午5:39:06
 * 操作产品表
 */
public interface ProductDao {
	//根据商品id查询
	Product queryById(@Param("productId")Integer productId);
	//根据价格区间查询
	public List<Product> queryByPriceRange(
			@Param("productName")String productName,
			@Param("lowPrice")Double lowPrice,
			@Param("highPrice")Double highPrice,
			@Param("begin")Integer begin, 
			@Param("end")Integer end);
	//根据价格区间查询的结果数量，用于分页
	public Integer queryByPriceRangeCount(
			@Param("productName")String productName,
			@Param("lowPrice")Double lowPrice,
			@Param("highPrice")Double highPrice);
}
