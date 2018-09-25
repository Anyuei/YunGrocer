package mapper;

import java.sql.ResultSet;

import com.YunGrocer.javabeans.Product;

import utils.RowMapper;

public class ProductMapper implements RowMapper<Product> {

	public Product rowMapper(ResultSet rs) {
		try {
			Product product = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("封装结果集错误！~");
		}
	}

}
