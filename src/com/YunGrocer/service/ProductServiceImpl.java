package com.YunGrocer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.YunGrocer.dao.ProductDao;
import com.YunGrocer.dao.ProductDaoImpl;
import com.YunGrocer.javabeans.Product;

import utils.JdbcUtil3;


public class ProductServiceImpl implements ProductService{

	public List<Product> findProduct(Integer currentPage, String productName,
			String lessOrGreat, Double price) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			ProductDao dao = new ProductDaoImpl();
			/**
			 * 计算分页
			 */
			Integer begin = (currentPage-1)*3+1; 
			Integer end = currentPage*3;
			List<Product> list = dao.queryByPage(begin, end, productName, lessOrGreat, price);
			conn.commit();
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
			JdbcUtil3.close(conn);
		}
	}

	public Product findById(Integer productId) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			ProductDao dao = new ProductDaoImpl();
			Product p = dao.queryById(productId);
			conn.commit();
			return p;
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
			JdbcUtil3.close(conn);
		}
	}
	/**
	 * 根据价格区间查找的 商品数量
	 */
	public Integer queryByPriceRangeCount(String productName,Double lowPrice, Double highPrice) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Integer counts=0;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			ProductDao dao = new ProductDaoImpl();
			counts = dao.queryByPriceRangeCount(productName,lowPrice, highPrice);
			conn.commit();
			return counts;
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
			JdbcUtil3.close(conn);
		}
	}

	/**
	 * 根据价格区间查找 商品
	 */
	public List<Product> queryByPriceRange(String productName,Integer currentPage,Double lowPrice, Double highPrice) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			ProductDao dao = new ProductDaoImpl();
			/**
			 * 计算分页
			 */
			Integer begin = (currentPage-1)*3+1; 
			Integer end = currentPage*3;
			List<Product> list = dao.queryByPriceRange(productName, lowPrice, highPrice, begin, end);
			conn.commit();
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
			JdbcUtil3.close(conn);
		}
	}
}
