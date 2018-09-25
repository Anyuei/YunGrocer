package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class JdbcTemplate {
	/**
	 * JdbcTemplate jdbcTemplate = new JdbcTemplate();

		String deleteSql = "delete from account where card_id=?";
		
		jdbcTemplate.executeUpdate(deleteSql,1);
		
		
		String insertSql = "insert into account values(a_seq.nextval,?,?,?,?)";
		
		jdbcTemplate.executeUpdate(insertSql,"hehe","123",10000.0,"1222112211");
	 * @param sql : ִ�е�sql���
	 * @param args �� ��sql�����ռλ����ֵ
	 */
	//  ͨ�õ� ��  ɾ ��  ����
	public void executeUpdate(String sql,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// ռλ����ֵ
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			pstm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
	//  ͨ�õ� ��ѯ  ����
	/*
	 * ��ѯһ������
	 */
	public <T> T executeQueryOne(String sql,RowMapper<T> rw,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// ռλ����ֵ
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			T t = null;
			while(rs.next()){
				// ���������� ����ѯ�������ݷ�װ��java����
				t = rw.rowMapper(rs);
			}
			return t;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("��ѯʧ�ܣ�~");
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
	/*
	 * ��ѯ��������
	 */
	public <T> List<T> executeQueryMany(String sql,RowMapper<T> rw,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// ռλ����ֵ
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			List<T> list = new ArrayList<T>();
			while(rs.next()){
				// ���������� ����ѯ�������ݷ�װ��java����
				T t = rw.rowMapper(rs);
				list.add(t);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("��ѯʧ�ܣ�~");
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
	/**
	 * ��ѯ���������
	 */
	public Integer executeQueryMany(String sql,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer counts=0;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// ռλ����ֵ
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			
			while(rs.next()){//��������ʱ
				// ���������� ����ѯ�������ݷ�װ��java����
				counts=rs.getInt(1);
			}
			return counts;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("��ѯʧ�ܣ�~");
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
}









