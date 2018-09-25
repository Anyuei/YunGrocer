package com.YunGrocer.service;

import com.YunGrocer.javabeans.YGUser;

public interface UserService {
	void regist(YGUser user);
	void modify(YGUser user);
	YGUser login(String username,String password);
}
