package com.YunGrocer.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.YunGrocer.javabeans.Product;
/**
 * ProductDao.java
 * @author anyunpei
 * 2018��9��23������5:39:06
 * ������Ʒ��
 */
public interface ProductDao {
	//������Ʒid��ѯ
	Product queryById(@Param("productId")Integer productId);
	//���ݼ۸������ѯ
	public List<Product> queryByPriceRange(
			@Param("productName")String productName,
			@Param("lowPrice")Double lowPrice,
			@Param("highPrice")Double highPrice,
			@Param("begin")Integer begin, 
			@Param("end")Integer end);
	//���ݼ۸������ѯ�Ľ�����������ڷ�ҳ
	public Integer queryByPriceRangeCount(
			@Param("productName")String productName,
			@Param("lowPrice")Double lowPrice,
			@Param("highPrice")Double highPrice);
}
