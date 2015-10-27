/**
 * 
 */
package com.gome.autodeploy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.service.app.ApplicationService;
//import com.gome.autodeploy.domain.app.Application;
//import com.gome.autodeploy.service.app.ApplicationService;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author bailu-ds
 *
 */
public class AppTest extends BaseSpringTestCase {

	@Autowired
	private WebApplicationContext wac;
//	private MockMvc mockMvc;
	
//	@Before
//	public void setup() throws Exception {
//		this.mockMvc =  webAppContextSetup(this.wac).build();
//	}
	 
//	@Test
//	public void controllerExceptionHandler() throws Exception {
//		this.mockMvc.perform(get("/app/to/new")).andExpect(status().isOk());
//	}
	 
	
	@Resource
	private ApplicationService applicationService;
	
	@Test
	public void queryApplicationTest () {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", "admin");
		List<Application> apps = applicationService.queryApplicationByMember(map);
		for (Application app : apps) {
			System.out.println(app.getName());
		}
	}
	
}
