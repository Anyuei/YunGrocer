package utils;

import java.sql.ResultSet;

public interface RowMapper<T> {
	/*
	 * ��������ʵ�ֵģ� �������Լ�ʵ�����ݿ����ʵ�����ӳ���ϵ
	 */
	T rowMapper(ResultSet rs);
}
