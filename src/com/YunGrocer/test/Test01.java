package com.YunGrocer.test;

import org.junit.jupiter.api.Test;

import com.YunGrocer.service.ProductServiceImpl;

import utils.MybatisUtil;

public class Test01{
	/**
	 * ��֤sql�Ự����
	 */
	@Test
	public void checkConnection() {
		System.out.println(MybatisUtil.openSession());
	}
	/**
	 * ���Բ��Ҹ���id����ĳ����
	 */
	@Test
	public void findById() {
		System.out.println(new ProductServiceImpl().findById(1));
	}
	
}
