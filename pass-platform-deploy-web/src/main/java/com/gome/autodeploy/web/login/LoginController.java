/**
 * 登录、退出
 */
package com.gome.autodeploy.web.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;





import org.acegisecurity.userdetails.memory.UserMap;
import org.apache.http.HttpRequest;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.service.auth.UserService;
import com.gome.autodeploy.web.BaseController;
import com.gome.autodeploy.common.EncryptUtil;
import com.gome.autodeploy.common.UserMseeage;

/**
 * @author bailu-ds
 *
 */
@Controller
public class LoginController extends BaseController {

	@Resource
	private UserService userService;
//	@RequestMapping(value = "/login", method= {RequestMethod.POST, RequestMethod.GET})
//	public ModelAndView login(String error) {
//		ModelAndView modelAndView = new ModelAndView();
//		Subject subject = SecurityUtils.getSubject();////		if (null == subject || !subject.isAuthenticated()) {
//			modelAndView.setViewName("/login.jsp");
//		} else {
//			modelAndView.setViewName("/home.jsp");
//		}
//		
//		if (null != error) {
//			modelAndView.addObject("err", messageSource.getMessage("shiro_msg_003", null, Locale.CHINA));
//		}
//		modelAndView.setViewName("/login.jsp");
//		return modelAndView;
//	}
	
	
	@RequestMapping(value = "/login", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView login(HttpSession httpSession,HttpServletRequest req) throws NoSuchAlgorithmException {
		EncryptUtil encryptUtil= new EncryptUtil();
		ModelAndView modelAndView = new ModelAndView();
		String userName=req.getParameter("userName");
		
		if(userName==null||userName.trim().isEmpty()){
			modelAndView.addObject("error", "请输入用户名");
			modelAndView.setViewName("/login.jsp");	
			return modelAndView;
		}	

		
		Map<String,String> error = new HashMap<String,String>();
		User user=userService.getUserByName(userName);

		if(user==null){
			modelAndView.addObject("error","用户不存在");
			modelAndView.setViewName("/login.jsp");
			return modelAndView;
		}
		String password=encryptUtil.Md5(req.getParameter("password"));
		if(!user.getPassword().equals(password)){
			modelAndView.addObject("error","密码错误");
			modelAndView.setViewName("/login.jsp");
			return modelAndView;
		}else{
			modelAndView.setViewName("/app-info.jsp");
			UserMseeage.saveUserMessage(req, user);
			
			
		}
			return modelAndView;
		}
	    
	
	@RequestMapping(value = "/logout", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse resp) {
			req.getSession().invalidate();
		ModelAndView modelAndView = new ModelAndView();
////		Subject subject = SecurityUtils.getSubject();
////		if (subject.isAuthenticated()) {
////			subject.logout();
////		}
		modelAndView.setViewName("/login.jsp");
		return modelAndView;
	}
	 
}
