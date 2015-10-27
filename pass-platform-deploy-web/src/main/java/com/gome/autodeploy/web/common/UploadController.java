/**
 * 
 */
package com.gome.autodeploy.web.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.CosUploadUtil;
import com.gome.autodeploy.web.BaseController;

/**
 * @author bailu-ds
 *
 */
@Controller
public class UploadController extends BaseController {

	@RequestMapping(value = "/upload", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/upload.jsp");
    	String file = request.getParameter("filePath");
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
    	String date = format.format(new Date());
    	if(!file.endsWith(".tar.gz")) {
    		modelAndView.addObject("error", "只支持 .tar.gz后缀的文件");
    		return modelAndView;
    	}
    	String domain = request.getParameter("domain");
    	String user = request.getParameter("user");
//    	String srcPath = "D://";	// 存相对路径
    	String srcPath = "/export/Upload/" + domain + "/" + user +"/";	// 存相对路径
    	String descPath = "/export/Upload/package/" + domain + "/" + date + "/";
        String fileName = user + "_" + format.format(new Date()) + ".tar.gz";
        try {
			CosUploadUtil.upload(fileName, domain, user, srcPath, descPath, request);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return modelAndView;
	}
	
}
