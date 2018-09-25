package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil3 {
	// 使用prop加载解析配置文件中的数据
	private static final Properties prop = new Properties();
	static{
		// 读取配置文件
		InputStream in = null;
		try{
			in = JdbcUtil3.class.getResourceAsStream("/jdbc.properties");
			prop.load(in);
			// 加载驱动
			Class.forName(prop.getProperty("jdbc.oracleDriver")); 
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("解析配置文件错误！~");
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// 创建一个ThreadLocal对象， 将Connection绑定到当前线程
	private static ThreadLocal<Connection> tol = new ThreadLocal<Connection>();

	public static Connection getConn(){
		try {
			// 每一次调用， 先到当前线程获取一下
			Connection conn = tol.get();
			if(conn==null){
				// 创建一个新的Connection 
				conn = DriverManager.getConnection(prop.getProperty("jdbc.url"), 
						prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));
				// 将其存入当前线程
				tol.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败！~");
		}
	}

	public static void close(ResultSet rs,PreparedStatement pstm,Connection conn){
		try{
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			if(conn!=null){
				conn.close();
				// 需要从当前线程移除conn
				tol.remove();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("释放资源错误！~");
		}
	}
	public static void close(PreparedStatement pstm,Connection conn){
		try{
			if(pstm!=null) pstm.close();
			if(conn!=null) {
				conn.close();
				// 需要从当前线程移除conn
				tol.remove();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("释放资源错误！~");
		}
	}
	public static void close(Connection conn){
		try{
			if(conn!=null) {
				conn.close();
				// 需要从当前线程移除conn
				tol.remove();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("释放资源错误！~");
		}
	}

}
