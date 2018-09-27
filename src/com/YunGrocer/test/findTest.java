package com.YunGrocer.test;

import org.junit.jupiter.api.Test;

import com.YunGrocer.service.ProductServiceImpl;

public class findTest {
	/**
	 * 测试查找根据id查找某本书
	 */
	@Test
	public void findById() {
		System.out.println(new ProductServiceImpl().findById(1));
	}
}
