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
	 * @param sql : 执行的sql语句
	 * @param args ： 给sql语句中占位符的值
	 */
	//  通用的 增  删 改  方法
	public void executeUpdate(String sql,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// 占位符赋值
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
	//  通用的 查询  方法
	/*
	 * 查询一条数据
	 */
	public <T> T executeQueryOne(String sql,RowMapper<T> rw,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// 占位符赋值
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			T t = null;
			while(rs.next()){
				// 处理结果集， 将查询到的数据封装成java对象
				t = rw.rowMapper(rs);
			}
			return t;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("查询失败！~");
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
	/*
	 * 查询多条数据
	 */
	public <T> List<T> executeQueryMany(String sql,RowMapper<T> rw,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// 占位符赋值
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			List<T> list = new ArrayList<T>();
			while(rs.next()){
				// 处理结果集， 将查询到的数据封装成java对象
				T t = rw.rowMapper(rs);
				list.add(t);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("查询失败！~");
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
	/**
	 * 查询结果的数量
	 */
	public Integer executeQueryMany(String sql,Object... args){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer counts=0;
		try{
			conn = JdbcUtil3.getConn();
			pstm = conn.prepareStatement(sql);
			// 占位符赋值
			if(args.length>0){
				for(int i=0;i<args.length;i++){
					pstm.setObject(i+1, args[i]);
				}
			}
			rs = pstm.executeQuery();
			
			while(rs.next()){//当有数据时
				// 处理结果集， 将查询到的数据封装成java对象
				counts=rs.getInt(1);
			}
			return counts;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("查询失败！~");
		}finally{
			JdbcUtil3.close(pstm, null);
		}
	}
}









