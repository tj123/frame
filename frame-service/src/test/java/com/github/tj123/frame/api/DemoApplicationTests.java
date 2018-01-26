package com.github.tj123.frame.api;

import com.github.tj123.frame.api.common.utils.AreaUtils;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {


//		String x = AreaUtils.addZeroTo("51", 4);
//		System.out.println(x);
//
//		System.out.println(AreaUtils.deleteLatterZero(x));
		System.out.println(AreaUtils.simple("0"));
		System.out.println(AreaUtils.simple("510000"));
		System.out.println(AreaUtils.simple("510100"));
		System.out.println(AreaUtils.simple("510110"));
		System.out.println(AreaUtils.simple("510185"));
		System.out.println(AreaUtils.simple("5101852"));
		System.out.println(AreaUtils.simple("51018521255122"));

	}

}
