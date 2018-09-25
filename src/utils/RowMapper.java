package utils;

import java.sql.ResultSet;

public interface RowMapper<T> {
	/*
	 * 给调用者实现的： 调用者自己实现数据库表与实体对象映射关系
	 */
	T rowMapper(ResultSet rs);
}
