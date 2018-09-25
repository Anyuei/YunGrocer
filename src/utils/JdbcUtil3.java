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
	// ʹ��prop���ؽ��������ļ��е�����
	private static final Properties prop = new Properties();
	static{
		// ��ȡ�����ļ�
		InputStream in = null;
		try{
			in = JdbcUtil3.class.getResourceAsStream("/jdbc.properties");
			prop.load(in);
			// ��������
			Class.forName(prop.getProperty("jdbc.oracleDriver")); 
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("���������ļ�����~");
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// ����һ��ThreadLocal���� ��Connection�󶨵���ǰ�߳�
	private static ThreadLocal<Connection> tol = new ThreadLocal<Connection>();

	public static Connection getConn(){
		try {
			// ÿһ�ε��ã� �ȵ���ǰ�̻߳�ȡһ��
			Connection conn = tol.get();
			if(conn==null){
				// ����һ���µ�Connection 
				conn = DriverManager.getConnection(prop.getProperty("jdbc.url"), 
						prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));
				// ������뵱ǰ�߳�
				tol.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�~");
		}
	}

	public static void close(ResultSet rs,PreparedStatement pstm,Connection conn){
		try{
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			if(conn!=null){
				conn.close();
				// ��Ҫ�ӵ�ǰ�߳��Ƴ�conn
				tol.remove();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("�ͷ���Դ����~");
		}
	}
	public static void close(PreparedStatement pstm,Connection conn){
		try{
			if(pstm!=null) pstm.close();
			if(conn!=null) {
				conn.close();
				// ��Ҫ�ӵ�ǰ�߳��Ƴ�conn
				tol.remove();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("�ͷ���Դ����~");
		}
	}
	public static void close(Connection conn){
		try{
			if(conn!=null) {
				conn.close();
				// ��Ҫ�ӵ�ǰ�߳��Ƴ�conn
				tol.remove();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("�ͷ���Դ����~");
		}
	}

}
