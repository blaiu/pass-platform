/**
 * 
 */
package com.gome.autodeploy;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author bailu-ds
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring-config.xml", "classpath*:spring/spring-mvc.xml", "classpath*:sqlmap/mybatis-config-mysql.xml"})
public class BaseSpringTestCase extends AbstractJUnit4SpringContextTests {

	
	
	public static void main (String[] arg) {
//		BaseSpringTestCase casea = new BaseSpringTestCase ();
//		casea.testQueryApplication();
	}
	
}
