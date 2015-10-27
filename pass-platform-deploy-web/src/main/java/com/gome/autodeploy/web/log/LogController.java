package com.gome.autodeploy.web.log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.autodeploy.web.BaseController;
import com.gome.pass.platform.log.LogApiImpl;
import com.gome.pass.platform.log.bean.Message;

/**
 * 
 * @author tankaili
 *
 */
@Controller
@RequestMapping(value = "/log")
public class LogController extends BaseController{
	
	private static Logger logger = Logger.getLogger(LogController.class);
     
	@Resource
	private LogApiImpl logApiImpl;
	
	
	/** 
	* @Title: manager 
	* @Description: 获取日志信息
	* @return
	* @throws   
	*/
	@SuppressWarnings("unused")
	@RequestMapping(value="/{allContainer}", method={RequestMethod.GET})
	@ResponseBody
	public List<Message> log(@PathVariable("allContainer") String allContainer) {
		
		Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(System.currentTimeMillis()-8*60*60*1000);
		Date startDate=calendar.getTime();
		
		List<Message> logMessages=new ArrayList<Message>(); 
		List<Message> logMessage=new ArrayList<Message>();
		
		List<Message> errors=new ArrayList<Message>();
				 
		if(StringUtils.isNotEmpty(allContainer)){

			 StringTokenizer st=new StringTokenizer(allContainer,"#");
		     while(st.hasMoreTokens()){ 
		        String containerID=st.nextToken();
		        System.out.println(containerID);
                try {

            		Message error=new Message();
            		error.setTime(" ");
            		error.setLog("无日志信息！");
            		errors.add(error);
            		
					logMessage = logApiImpl.getLog(containerID, null, startDate, "ERROR");
					logMessages.addAll(logMessage);
							
				} catch (Exception e) {
					logger.error("  logApiImpl 接口调用异常 "+e.getMessage());
					//throw new PaasException(PaasException.INTERFACE_ERROR, " 查询日志接口调用异常"+e.getMessage());
					e.printStackTrace();
					//return errors;
				} 
		     }
		     
		     if(logMessages!=null && !logMessages.isEmpty())
     		{
         		return logMessages;
     		}else{
     			return errors;
     		}
		     
		}else{
			logger.error("查询容器实例信息出错");
		}

			return null;

	}
}
