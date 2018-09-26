package com.YunGrocer.test;

public class Split {
	public static void main(String[] args) {
		String string="asd.ased.dasd";
		String[] strings=string.split("\\.");
		for (String string2 : strings) {
			System.out.println(string2);
		}
	}
}
