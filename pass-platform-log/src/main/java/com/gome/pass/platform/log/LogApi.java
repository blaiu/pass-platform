/**
 * 
 */
package com.gome.pass.platform.log;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gome.pass.platform.log.bean.Message;

/**
 * @author bailu-ds
 *
 */
public interface LogApi {

	/**
	 * 获取file日志信息 每次获取十分钟之内的信息
	 * @param file 容器ID
	 * @param startDate 开始时间
	 * @return
	 */
	public List<Message> getLog(String file, String scrollId, Date startDate, String keyword) throws JsonParseException, JsonMappingException, IOException;
	
}
