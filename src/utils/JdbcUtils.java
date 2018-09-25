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
	 * ��̬��ʼ�������
	 * ��Ҫ����������ص�ʱ�򣬼���һ�Σ�������������ļ��ļ���
	 * ����ͨ�������ļ������ز�ͬ��jdbc������
	 */
	static {
		//����������
		InputStream in=null;
		//���Զ�ȡ�����ļ�
		try {
			//��/��ͷ����˼Ϊ·����src��Ŀ¼��ʼ������src/jdbc.day02/jdbc.properties,·����д����
			in=JdbcUtils.class.getResourceAsStream("/jdbc.properties");
			//��ȡ�����ļ���������PROPERTIES��
			PROPERTIES.load(in);
			//ͨ�������ļ��е�keyֵ����value����������ء�
			Class.forName(PROPERTIES.getProperty("jdbc.oracleDriver"));
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�������ó���~");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * �����������ͨ�������ഴ������
	 * @return
	 */
	private static ThreadLocal<Connection> tol = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		// ��������
		
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
			throw new RuntimeException("��ȡ����ʧ��!~");
		}
	}

	// �ͷ���Դ
	public static void close(ResultSet rs, PreparedStatement pstm, Connection conn) {
		// �����ͷ���Դ

		try {
			if (rs != null) { // �����ָ���쳣
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
			throw new RuntimeException("�ͷ���Դʧ�ܣ�");
		}
	}

	public static void close(PreparedStatement pstm, Connection conn) {
		// �����ͷ���Դ
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
			throw new RuntimeException("�ͷ���Դʧ�ܣ�");
		}
	}

	public static void close(Connection conn) {
		// �����ͷ���Դ
		try {

			if (conn != null) {
				conn.close();
				tol.remove();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�ͷ���Դʧ�ܣ�");
		}
	}
}
