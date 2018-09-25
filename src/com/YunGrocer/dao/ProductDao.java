package com.YunGrocer.dao;
import java.util.List;
import com.YunGrocer.javabeans.Product;
/**
 * ProductDao.java
 * @author anyunpei
 * 2018年9月23日下午5:39:06
 * 操作产品表
 */
public interface ProductDao {
	//根据页面查询，和一些相关的条件
	List<Product> queryByPage(Integer begin,Integer end
			,String productName,String lessOrGreat,Double price);
	//根据商品id查询
	Product queryById(Integer productId);
	//根据价格区间查询
	public List<Product> queryByPriceRange(String productName,Double lowPrice,Double highPrice,Integer begin, Integer end);
	//根据价格区间查询的结果数量，用于分页
	public Integer queryByPriceRangeCount(String productName,Double lowPrice,Double highPrice);
}
