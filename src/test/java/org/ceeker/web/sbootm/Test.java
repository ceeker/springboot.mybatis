package org.ceeker.web.sbootm;

public class Test {
	public static void main(String[] args) {
		BuilderTest test=new BuilderTest.Builder().name("zxl").address("hh").build();
		System.out.println(test.getName());
	}
}
