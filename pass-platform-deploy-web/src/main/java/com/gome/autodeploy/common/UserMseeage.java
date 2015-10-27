package com.gome.autodeploy.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.gome.autodeploy.domain.auth.User;

public class UserMseeage {
	public static void saveUserMessage(HttpServletRequest req, User user){
		HttpSession httpSession = req.getSession();
		Integer id = user.getUserId();
		httpSession.setAttribute("username", user.getUserName()); 
		httpSession.setAttribute("id", id);
		
	}
	
	public static User getUserMessages(HttpServletRequest req){
		HttpSession httpSession = req.getSession();
		User user=new User();
		Integer userId = (Integer) httpSession.getAttribute("id");
		String username =(String) httpSession.getAttribute("username");
		user.setUserId(userId);
		user.setUserName(username);
		return user;
		
	}
	
	
	
}
