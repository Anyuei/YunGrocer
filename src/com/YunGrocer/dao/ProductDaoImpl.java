package com.YunGrocer.dao;

import java.util.List;

import com.YunGrocer.javabeans.Product;

import mapper.ProductMapper;
import utils.JdbcTemplate;
public class ProductDaoImpl implements ProductDao{
	private final String QUERY_BY_PAGE = "select * from (select sp.*,rownum rn from YG_product sp) where rn>=? and rn<=?";
	// productName 不为null  price 不为  null 并且  opt 选择的是小于
	private final String QUERY_BY_PAGE_PRODUCTNAME_LESSPRICE = "select * from (select sp.*,rownum rn from " +
			"(select * from YG_product where productName like ? and price <?) sp) where rn>=? and rn<=?";
	// productName 不为null  price 不为  null 并且  opt 选择的是大于
	private final String QUERY_BY_PAGE_PRODUCTNAME_GREATPRICE = "select * from (select sp.*,rownum rn from " +
			"(select * from YG_product where productName like ? and price >?) sp) where rn>=? and rn<=?";
	// productName 不为null  price 为  null
	private final String QUERY_BY_PAGE_PRODUCTNAME = "select * from (select sp.*,rownum rn from " +
			"(select * from YG_product where productName like ?) sp) where rn>=? and rn<=?";
	// productName 为null  price 不为  null 并且  opt 选择的是大于
	private final String QUERY_BY_PAGE_GREATPRICE = "select * from (select sp.*,rownum rn from " +
			"(select * from YG_product where productName price >?) sp) where rn>=? and rn<=?";
	// productName 为null  price 不为  null 并且  opt 选择的是小于
	private final String QUERY_BY_PAGE_LESSPRICE = "select * from (select sp.*,rownum rn from " +
			"(select * from YG_product where productName price <?) sp) where rn>=? and rn<=?";

	private JdbcTemplate jte = new JdbcTemplate();
	public List<Product> queryByPage(Integer begin, Integer end,
			String productName, String lessOrGreat, Double price) {
		List<Product> list = null;
		if(productName==null&&price==null){//1
			list = jte.executeQueryMany(QUERY_BY_PAGE, new ProductMapper(), begin,end);
		}else if(productName==null&&price!=null&&"1".equals(lessOrGreat)){//2
			list = jte.executeQueryMany(QUERY_BY_PAGE_LESSPRICE, new ProductMapper(), 
					price,begin,end);
		}else if(productName==null&&price!=null&&"2".equals(lessOrGreat)){//3
			list = jte.executeQueryMany(QUERY_BY_PAGE_GREATPRICE, new ProductMapper(), 
					price,begin,end);
		}else if(productName!=null&&price!=null&&"1".equals(lessOrGreat)){//4
			list = jte.executeQueryMany(QUERY_BY_PAGE_PRODUCTNAME_LESSPRICE, new ProductMapper(), 
					"%"+productName+"%",price,begin,end);
		}else if(productName!=null&&price!=null&&"2".equals(lessOrGreat)){//4
			list = jte.executeQueryMany(QUERY_BY_PAGE_PRODUCTNAME_GREATPRICE, new ProductMapper(), 
					"%"+productName+"%",price,begin,end);
		}else{//6
			list = jte.executeQueryMany(QUERY_BY_PAGE_PRODUCTNAME, new ProductMapper(), 
					"%"+productName+"%",begin,end);
		}
		return list;
	}
	//根据商品id查询
	public Product queryById(Integer productId) {
		return jte.executeQueryOne("select * from YG_product where id=?", 
					new ProductMapper(), productId);
	}
	
	//根据价格区间与商品名模糊查询
	public List<Product> queryByPriceRange(String productName,Double lowPrice,Double highPrice,Integer begin, Integer end) {
		List<Product> list = null;
		/**
		 * 查询所有
		 */
		if (lowPrice==null&&highPrice==null) {
			list= jte.executeQueryMany("select * from (select sp.*,rownum rn from "
					+ "(select * from YG_product where productName like ?)sp) where rn>=? and rn<=?", 
						new ProductMapper(),"%"+productName+"%",begin,end);
		}
		/**
		 * 最低价格为空，最高价格设限
		 */
		else if (lowPrice==null&&highPrice!=null) {
			list= jte.executeQueryMany("select * from (select sp.*,rownum rn from "
					+ "(select * from YG_product where productName like ? and price<=?)sp) where rn>=? and rn<=?", 
						new ProductMapper(),"%"+productName+"%",highPrice,begin,end);
		}
		/**
		 * 最低价格非空，最高价格非空
		 */
		else if (lowPrice!=null&&highPrice!=null) {
			list= jte.executeQueryMany("select * from (select sp.*,rownum rn from "
					+ "(select * from YG_product where productName like ? and price>=? and price<=?)sp) where rn>=? and rn<=?", 
					    new ProductMapper(),"%"+productName+"%",lowPrice,highPrice,begin,end);
		}
		/**
		 * 最低价格非空，最高价格不设限（土豪必备）
		 */
		else if (lowPrice!=null&&highPrice==null) {
			list= jte.executeQueryMany("select * from (select sp.*,rownum rn from "
					+ "(select * from YG_product where productName like ? and price>=?)sp) where rn>=? and rn<=?", 
					    new ProductMapper(),"%"+productName+"%",lowPrice,begin,end);
		}
		return list;
	}
	//根据价格区间查询的结果数量
	public Integer queryByPriceRangeCount(String productName,Double lowPrice,Double highPrice) {
		Integer counts=0;
		System.out.println("pN"+productName+"low"+lowPrice+"high"+highPrice);
		if (lowPrice==null&&highPrice==null) {
			counts =jte.executeQueryMany("select max(rownum) from YG_product where productName like ?", 
					"%"+productName+"%");
		}else if (lowPrice==null&&highPrice!=null) {
			counts =jte.executeQueryMany("select max(rownum) from YG_product where productName like ? and price<=?", 
					"%"+productName+"%",highPrice);
		}else if (lowPrice!=null&&highPrice!=null) {
			counts =jte.executeQueryMany("select max(rownum) from YG_product where productName like ? price>=? and price<=?", 
				"%"+productName+"%",lowPrice,highPrice);
		}else if (lowPrice!=null&&highPrice==null) {
			counts =jte.executeQueryMany("select max(rownum) from YG_product where productName like ? and price>=?", 
				"%"+productName+"%",lowPrice);
		}
		System.out.println("dao层"+counts);
		return counts;
		
	}
}











