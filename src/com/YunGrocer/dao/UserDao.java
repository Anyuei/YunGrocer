package com.YunGrocer.dao;

import com.YunGrocer.javabeans.YGUser;
/**
 * UserDao.java
 * @author anyunpei
 *2018��9��23������5:38:24
 *�����û���
 */
public interface UserDao {
	/**
	 * ����û�
	 * @param user
	 */
	void addUser(YGUser user);
	/**
	 * �޸��û�
	 * @param user
	 */
	void updateUser(YGUser user);
	/**
	 * �����û��������� ��ѯ
	 * @param username
	 * @param password
	 * @return
	 */
	YGUser queryByUsernameAndPassword(String username,String password);
	/**
	 * �����û�����ѯ
	 * @param username
	 * @return
	 */
	YGUser queryByUsername(String username);
}
