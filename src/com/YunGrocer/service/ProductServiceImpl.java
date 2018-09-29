package com.YunGrocer.service;

import java.util.List;
import com.YunGrocer.dao.ProductDao;
import com.YunGrocer.javabeans.Product;
import utils.MybatisUtil;


public class ProductServiceImpl implements ProductService{

/*	public List<Product> findProduct(Integer currentPage, String productName,
			String lessOrGreat, Double price) {
		//Connection conn = null;
		try{
			//conn = JdbcUtil3.getConn();
			//conn.setAutoCommit(false);
			ProductDao dao = MybatisUtil.getMapper(ProductDao.class);
			*//**
			 * 计算分页
			 *//*
			Integer begin = (currentPage-1)*3+1; 
			Integer end = currentPage*3;
			List<Product> list = dao.queryByPage(begin, end, productName, lessOrGreat, price);
			//conn.commit();
			return list;
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("操作失败");
		}finally{
			//JdbcUtil3.close(conn);
			MybatisUtil.close();
		}
	}*/

	public Product findById(Integer productId) {
		try{
			//通过MybatisUtil工具类获取 dao
			ProductDao dao = MybatisUtil.getMapper(ProductDao.class);
			//使用dao 根据id 查询产品 
			Product p = dao.queryById(productId);
			return p;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("操作失败");
		}finally{
			MybatisUtil.close();
		}
	}
	
	/**
	 * 根据价格区间查找 商品
	 */
	public List<Product> queryByPriceRange(String productName,Integer currentPage,Double lowPrice, Double highPrice) {
		//Connection conn = null;
		try{
			//conn = JdbcUtil3.getConn();
			//conn.setAutoCommit(false);
			//ProductDao dao = new ProductDaoImpl();
			ProductDao dao=MybatisUtil.getMapper(ProductDao.class);
			/**
			 * 计算分页
			 */
			Integer begin = (currentPage-1)*3+1; 
			Integer end = currentPage*3;
			List<Product> list = dao.queryByPriceRange(productName, lowPrice, highPrice, begin, end);
			//conn.commit();
			return list;
		}catch(Exception e){
			/*try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			e.printStackTrace();
			throw new RuntimeException("操作失败");
		}finally{
			//JdbcUtil3.close(conn);
			MybatisUtil.close();
		}
	}
	/**
	 * 根据价格区间查找的 商品数量
	 */
	public Integer queryByPriceRangeCount(String productName,Double lowPrice, Double highPrice) {
		// TODO Auto-generated method stub
		//Connection conn = null;
		Integer counts=0;
		try{
			//conn = JdbcUtil3.getConn();
			//conn.setAutoCommit(false);
			ProductDao dao = MybatisUtil.getMapper(ProductDao.class);
			counts = dao.queryByPriceRangeCount(productName,lowPrice, highPrice);
			//conn.commit();
			return counts;
		}catch(Exception e){
			/*try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			e.printStackTrace();
			throw new RuntimeException("操作失败");
		}finally{
			//JdbcUtil3.close(conn);
			MybatisUtil.close();
		}
	}

}
