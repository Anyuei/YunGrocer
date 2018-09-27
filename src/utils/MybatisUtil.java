package utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory factory;
	static {
		InputStream in=null;
		try {
			in=Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初始化工厂失败");
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static ThreadLocal<SqlSession> tol =new ThreadLocal<SqlSession>();
	/**
	 * 从本地线程池 获取 会话，获取不到会话 则新建会话
	 * @return
	 */
	public static SqlSession openSession() {
		SqlSession sqlSession = tol.get();
		if (sqlSession==null) {
			sqlSession = factory.openSession();
			tol.set(sqlSession);
		}
		return sqlSession;
	}
	/**
	 * 释放资源
	 */
	public static void close() {
		SqlSession sqlSession = openSession();
		if (sqlSession!=null) {
			sqlSession.close();
			tol.remove();
		}
	}
	/**
	 * 获取Dao实现类的对象
	 */
	public static <T> T getMapper(Class<T> c) {
		SqlSession sqlSession = openSession();
		T t = sqlSession.getMapper(c);
		return t;
	}
	/**
	 * 提交事务
	 */
	public static void commit(){
		openSession().commit();
	}
	/**
	 * 回滚事务
	 */
	public static void rollback(){
		openSession().rollback();
	}
}

