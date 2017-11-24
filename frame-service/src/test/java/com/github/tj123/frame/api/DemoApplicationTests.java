package com.github.tj123.frame.api;

import com.github.tj123.frame.service.common.AreaUtil;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {


//		String x = AreaUtil.addZeroTo("51", 4);
//		System.out.println(x);
//
//		System.out.println(AreaUtil.deleteLatterZero(x));
		System.out.println(AreaUtil.simple("0"));
		System.out.println(AreaUtil.simple("510000"));
		System.out.println(AreaUtil.simple("510100"));
		System.out.println(AreaUtil.simple("510110"));
		System.out.println(AreaUtil.simple("510185"));
		System.out.println(AreaUtil.simple("5101852"));
		System.out.println(AreaUtil.simple("51018521255122"));

	}

}
