package com.YunGrocer.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.YunGrocer.dao.UserDao;
import com.YunGrocer.dao.UserDaoImpl;
import com.YunGrocer.javabeans.YGUser;

import utils.JdbcUtil3;




public class UserServiceImpl implements UserService{
	/**
	 * ע��
	 */
	public void regist(YGUser user) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			UserDao dao = new UserDaoImpl();
			dao.addUser(user);
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}finally{
			JdbcUtil3.close(conn);
		}
	}
	/**
	 * �޸�
	 */
	public void modify(YGUser user) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			UserDao dao = new UserDaoImpl();
			dao.updateUser(user);
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}finally{
			JdbcUtil3.close(conn);
		}
	}
	/**
	 * ��¼��֤
	 */
	public YGUser login(String username, String password) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			UserDaoImpl dao = new UserDaoImpl();
			YGUser user = dao.queryByUsernameAndPassword(username, password);
			conn.commit();
			return user;
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}finally{
			JdbcUtil3.close(conn);
		}
	}
	/**
	 * �����ǳƲ����û�
	 */

	public YGUser findByName(String username) {
		Connection conn = null;
		try{
			conn = JdbcUtil3.getConn();
			conn.setAutoCommit(false);
			UserDaoImpl dao = new UserDaoImpl();
			YGUser user = dao.queryByUsername(username);
			conn.commit();
			return user;
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}finally{
			JdbcUtil3.close(conn);
		}
	}

}
