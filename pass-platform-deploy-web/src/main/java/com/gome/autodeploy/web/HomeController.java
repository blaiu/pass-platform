/**
 * 
 */
package com.gome.autodeploy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author bailu-ds
 *
 */
@Controller
public class HomeController extends BaseController {

	@RequestMapping(value = "/home", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home.jsp");
		return modelAndView;
	}
	
}