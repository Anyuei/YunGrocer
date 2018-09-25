package com.YunGrocer.dao;
import java.util.List;
import com.YunGrocer.javabeans.Product;
/**
 * ProductDao.java
 * @author anyunpei
 * 2018��9��23������5:39:06
 * ������Ʒ��
 */
public interface ProductDao {
	//����ҳ���ѯ����һЩ��ص�����
	List<Product> queryByPage(Integer begin,Integer end
			,String productName,String lessOrGreat,Double price);
	//������Ʒid��ѯ
	Product queryById(Integer productId);
	//���ݼ۸������ѯ
	public List<Product> queryByPriceRange(String productName,Double lowPrice,Double highPrice,Integer begin, Integer end);
	//���ݼ۸������ѯ�Ľ�����������ڷ�ҳ
	public Integer queryByPriceRangeCount(String productName,Double lowPrice,Double highPrice);
}
