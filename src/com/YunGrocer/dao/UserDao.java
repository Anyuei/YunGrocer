package com.YunGrocer.dao;

import com.YunGrocer.javabeans.YGUser;
/**
 * UserDao.java
 * @author anyunpei
 *2018年9月23日下午5:38:24
 *操作用户表
 */
public interface UserDao {
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(YGUser user);
	/**
	 * 修改用户
	 * @param user
	 */
	void updateUser(YGUser user);
	/**
	 * 根据用户名和密码 查询
	 * @param username
	 * @param password
	 * @return
	 */
	YGUser queryByUsernameAndPassword(String username,String password);
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	YGUser queryByUsername(String username);
}
