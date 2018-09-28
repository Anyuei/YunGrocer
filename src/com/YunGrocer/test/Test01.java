package com.YunGrocer.test;

import org.junit.jupiter.api.Test;

import com.YunGrocer.service.ProductServiceImpl;

import utils.MybatisUtil;

public class Test01{
	/**
	 * 验证sql会话连接
	 */
	@Test
	public void checkConnection() {
		System.out.println(MybatisUtil.openSession());
	}
	/**
	 * 测试查找根据id查找某本书
	 */
	@Test
	public void findById() {
		System.out.println(new ProductServiceImpl().findById(1));
	}
	
}
