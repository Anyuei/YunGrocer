package com.YunGrocer.test;

import org.junit.jupiter.api.Test;

import com.YunGrocer.service.ProductServiceImpl;

public class findTest {
	/**
	 * ���Բ��Ҹ���id����ĳ����
	 */
	@Test
	public void findById() {
		System.out.println(new ProductServiceImpl().findById(1));
	}
}
