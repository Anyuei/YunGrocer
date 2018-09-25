package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JdbcUtils {
	private static final Properties PROPERTIES =new Properties();
	/**
	 * 静态初始化代码块
	 * 主要作用在类加载的时候，加载一次，具体完成配置文件的加载
	 * 并且通过配置文件来加载不同的jdbc驱动类
	 */
	static {
		//输入流声明
		InputStream in=null;
		//尝试读取配置文件
		try {
			//以/开头，意思为路径从src根目录开始：比如src/jdbc.day02/jdbc.properties,路径填写如下
			in=JdbcUtils.class.getResourceAsStream("/jdbc.properties");
			//读取配置文件，保存在PROPERTIES中
			PROPERTIES.load(in);
			//通过配置文件中的key值，把value的驱动类加载。
			Class.forName(PROPERTIES.getProperty("jdbc.oracleDriver"));
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("加载配置出错！~");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 有了驱动类后，通过驱动类创建连接
	 * @return
	 */
	private static ThreadLocal<Connection> tol = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		// 声明连接
		
		try {
			Connection conn = tol.get();
			if (conn == null) {
				conn = DriverManager.getConnection(PROPERTIES.getProperty("jdbc.url"),
						PROPERTIES.getProperty("jdbc.username"), PROPERTIES.getProperty("jdbc.password"));
				tol.set(conn);
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败!~");
		}
	}

	// 释放资源
	public static void close(ResultSet rs, PreparedStatement pstm, Connection conn) {
		// 尝试释放资源

		try {
			if (rs != null) { // 避免空指针异常
				rs.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
				tol.remove();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("释放资源失败！");
		}
	}

	public static void close(PreparedStatement pstm, Connection conn) {
		// 尝试释放资源
		try {
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
				tol.remove();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("释放资源失败！");
		}
	}

	public static void close(Connection conn) {
		// 尝试释放资源
		try {

			if (conn != null) {
				conn.close();
				tol.remove();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("释放资源失败！");
		}
	}
}
