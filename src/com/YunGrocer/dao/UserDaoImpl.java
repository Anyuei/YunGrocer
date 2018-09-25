package com.YunGrocer.dao;

import com.YunGrocer.javabeans.YGUser;

import mapper.UserMapper;
import utils.JdbcTemplate;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jte = new JdbcTemplate();

	public void addUser(YGUser user) {
		jte.executeUpdate("insert into YG_user values(?,?,?,?,?)", user.getUsername(), user.getPassword(),
				user.getName(), user.getZip(), user.getAddress());
	}

	public void updateUser(YGUser user) {
		jte.executeUpdate("update YG_user set username=?,password=?,name=?,zip=?,address=? where username=?",
				user.getUsername(), user.getPassword(), user.getName(), user.getZip(), user.getAddress(),
				user.getUsername());
	}

	public YGUser queryByUsernameAndPassword(String username, String password) {
		YGUser user = jte.executeQueryOne("select * from YG_user where username=? and password=?", new UserMapper(),
				username, password);
		System.out.println(user);
		return user;
	}
	public YGUser queryByUsername(String username) {
		YGUser user = jte.executeQueryOne("select * from YG_user where username=?", new UserMapper(),
				username);
		System.out.println(user);
		return user;
	}

}
