/**
 * 
 */
package com.gome.autodeploy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gome.autodeploy.common.RuntimeUtil;

/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestController extends BaseController {

	@RequestMapping(value="/deploy", method={RequestMethod.GET, RequestMethod.POST})
	public void deploy() {
		RuntimeUtil.deployDownLoad("192.168.137.102", "deploy.gome.com.cn", "2016", "bailu-201611073400-auto-deploy-web.tar.gz", "http://192.168.137.102/Upload/package/deploy.gome.com.cn/2016/bailu-201611073400-auto-deploy-web.tar.gz");
		RuntimeUtil.deployBackUp("192.168.137.102", "deploy.gome.com.cn", "1101", "deploy.gome.com/2016/bailu-201611073400-auto-deploy-web.tar.gz");
		RuntimeUtil.deployDecompress("192.168.137.103", "deploy.gome.com.cn", "2016", "bailu-201611073400-auto-deploy-web.tar.gz");
		RuntimeUtil.deployConfig("192.168.137.102", "deploy.gome.com.cn");
		RuntimeUtil.deployStart("192.168.137.103", "deploy.gome.com.cn");
	}
	
	@RequestMapping(value="/rollback", method={RequestMethod.GET, RequestMethod.POST})
	public void rollback() {
		RuntimeUtil.rollBackDownLoad("192.168.137.103", "deploy.gome.com.cn", "1101", "192.168.137.102");
		RuntimeUtil.rollBackDecompress("192.168.137.103", "deploy.gome.com.cn", "1101");
		RuntimeUtil.rollBackConfig("192.168.137.103", "deploy.gome.com.cn", "1101");
		RuntimeUtil.rollBackStart("192.168.137.103", "deploy.gome.com.cn");
	}
	
	@RequestMapping(value="/restart", method={RequestMethod.GET, RequestMethod.POST})
	public void restart() {
		RuntimeUtil.reStart("192.168.137.103", "deploy.gome.com.cn");
	}
	
	@RequestMapping(value="/extract", method={RequestMethod.GET, RequestMethod.POST})
	public void extract() {
		RuntimeUtil.extract("192.168.137.102", "deploy.gome.com.cn", "deploy.gome.com.cn/2016/bailu-201611073400-auto-deploy-web.tar.gz", "WEB-INF/spring/spring-config.xml");
	}
	
}
